package br.com.zupacademy.fabiano.mercadolivre.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Bucket implements Uploader{
    @Override
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(imagem -> "http://googlebucket.com/" + imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
