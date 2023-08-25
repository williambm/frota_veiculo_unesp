package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.db.model.Imagem;
import br.unesp.frotaveiculos.adapters.db.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    //Todo: teste de upload de imagem
    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping
    public ResponseEntity<Imagem> testeUploadImg(@RequestParam MultipartFile img) {
        try {
            Imagem imgUpload = Imagem.builder()
                    .nomeArquivo(img.getName())
                    .tamanhoFoto(img.getSize())
                    .imagem(img.getBytes())
                    .build();

            imgUpload = imagemRepository.save(imgUpload);
            return ResponseEntity.ok().body(imgUpload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
