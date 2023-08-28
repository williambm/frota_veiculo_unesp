package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.db.model.Imagem;
import br.unesp.frotaveiculos.adapters.db.repository.ImagemRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    //Todo: teste de upload de imagem
    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping
    public ResponseEntity<Imagem> testeUploadImg(@RequestParam MultipartFile img) {
        try {
            //Eu posso receber em Base64 e converter em binário (byte[]) para persistir com até 33% de menor tamanho.
            Imagem imgUpload = Imagem.builder()
                    //.nomeArquivo(img.getName()) //Esse getName vai retornar apenas o nome da variavel do multipartFile - no caso img
                    .nomeArquivo(img.getOriginalFilename())
                    .tamanhoFoto(img.getSize())
                    .imagemBinario(img.getBytes())
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
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img.getImagem());
//    }
    @GetMapping("/{id}")
    public ResponseEntity<ImagemDTO> getImagemById(@PathVariable Long id){
        Imagem img = imagemRepository.findById(id).orElseThrow();

        ImagemDTO dto = ImagemDTO.builder()
                .id(img.getId())
                .nomeArquivo(img.getNomeArquivo())
                .tamanhoFoto(img.getTamanhoFoto())
                .imagem(Base64.getEncoder().encodeToString(img.getImagemBinario())) //Converter em Base64 para o fronte poder renderizar mais fácil
                .build();

        return ResponseEntity.ok().body(dto);
    }
}

@Builder
@Data
class ImagemDTO{
    private Long id;
    private String nomeArquivo;

    private String imagem;
    private Long tamanhoFoto;
}
