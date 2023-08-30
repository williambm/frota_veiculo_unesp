package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.db.model.Imagem;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.CategoriaImg;
import br.unesp.frotaveiculos.adapters.db.repository.ImagemRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    //Todo: Ajustar melhor essas paradas - documentar a arquitetura
    /**
     * Na modelagem quero compartilhar imagem para veículos e funcionários. Dado isso terei a obrigatoriedade de ter uma
     * imagem padrão para funcionario e outra para carro.
     */

    //Todo: teste de upload de imagem
    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping
    public ResponseEntity<Imagem> testeUploadImg(@RequestParam MultipartFile img, @RequestParam CategoriaImg categoria) {
        try {
            //Eu posso receber em Base64 e converter em binário (byte[]) para persistir com até 33% de menor tamanho.
            Imagem imgUpload = Imagem.builder()
                    //.nomeArquivo(img.getName()) //Esse getName vai retornar apenas o nome da variavel do multipartFile - no caso img
                    .nomeArquivo(img.getOriginalFilename())
                    .tamanhoFoto(img.getSize())
                    .imagemBinario(img.getBytes())
                    .categoriaImg(categoria)
                    .build();

            //Armazeno a imagem em bytes no BD
            imgUpload = imagemRepository.save(imgUpload);
            return ResponseEntity.ok().body(imgUpload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Desta forma devolvo apenas a imagem porque o ResponseEntity está tipado como byte[]
//    @GetMapping("/{id}")
//    public ResponseEntity<byte[]> getImagemById(@PathVariable Long id){
//        Imagem img = imagemRepository.findById(id).orElseThrow();
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img.getImagemBinario());
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ImagemDTO> getImagemById(@PathVariable Long id) {
//        Imagem img = imagemRepository.findById(id).orElseThrow();
//
//        ImagemDTO dto = ImagemDTO.builder()
//                .id(img.getId())
//                .nomeArquivo(img.getNomeArquivo())
//                .tamanhoFoto(img.getTamanhoFoto())
//                .imagem(Base64.getEncoder().encodeToString(img.getImagemBinario())) //Converter em Base64 para o fronte poder renderizar mais fácil
//                .build();
//
//        return ResponseEntity.ok().body(dto);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getImagemById(@PathVariable Long id) {
        //capturo do BD
        Imagem img = imagemRepository.findById(id).orElseThrow();
        //Transformo em um fluxo de dados
        InputStreamResource streamResource = new InputStreamResource(new ByteArrayInputStream(img.getImagemBinario()));

        //Ajustar o Header
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.IMAGE_PNG);
//        if (img.getNomeArquivo().contains("*.png") || img.getNomeArquivo().contains(".PNG")) {
//            httpHeaders.setContentType(MediaType.IMAGE_PNG);
//        } else {
//            httpHeaders.setContentType(MediaType.IMAGE_JPEG);
//        }

        return ResponseEntity.ok().headers(httpHeaders).body(streamResource);
    }

}

@Builder
@Data
class ImagemDTO {
    private Long id;
    private String nomeArquivo;

    private String imagem;
    private Long tamanhoFoto;
}
