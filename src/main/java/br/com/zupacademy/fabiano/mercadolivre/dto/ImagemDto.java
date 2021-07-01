package br.com.zupacademy.fabiano.mercadolivre.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.List;

public class ImagemDto {
    @Size(min = 1)
    List<MultipartFile> imagens;

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
