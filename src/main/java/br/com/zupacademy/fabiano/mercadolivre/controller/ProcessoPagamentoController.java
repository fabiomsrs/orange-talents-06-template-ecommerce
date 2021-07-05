package br.com.zupacademy.fabiano.mercadolivre.controller;

import br.com.zupacademy.fabiano.mercadolivre.dto.PagSeguroTentativaPagamentoDto;
import br.com.zupacademy.fabiano.mercadolivre.dto.PayPalTentativaPagamentoDto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.StatusTransacao;
import br.com.zupacademy.fabiano.mercadolivre.modelo.TentativaPagamento;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import br.com.zupacademy.fabiano.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.fabiano.mercadolivre.repository.TentativaPagamentoRepository;
import br.com.zupacademy.fabiano.mercadolivre.utils.EnviadorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ProcessoPagamentoController {
    @Autowired
    TentativaPagamentoRepository repository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    EnviadorEmail sendGrid;

    @GetMapping("pagamento-pagseguro")
    public ResponseEntity<?> pagseguroProcessamento(@Valid PagSeguroTentativaPagamentoDto dto){
        Usuario usuario = (Usuario)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TentativaPagamento tentativaPagamento = dto.converter(compraRepository);
        repository.save(tentativaPagamento);

        String msg = "compra falhou";
        if(tentativaPagamento.getStatus() == StatusTransacao.SUCESSO){
            msg = "compra foi um sucesso";
            enviarRequestsParaServicosTerceiros(usuario, tentativaPagamento);
        }
        sendGrid.enviaEmail(msg);
        return ResponseEntity.ok(tentativaPagamento);
    }

    @GetMapping("pagamento-paypal")
    public ResponseEntity<?> paypalProcessamento(@Valid PayPalTentativaPagamentoDto dto){
        Usuario usuario = (Usuario)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TentativaPagamento tentativaPagamento = dto.converter(compraRepository);
        repository.save(tentativaPagamento);

        String msg = "compra falhou";
        if(tentativaPagamento.getStatus() == StatusTransacao.SUCESSO){
            msg = "compra foi um sucesso";
            enviarRequestsParaServicosTerceiros(usuario, tentativaPagamento);
        }
        sendGrid.enviaEmail(msg);
        return ResponseEntity.ok(tentativaPagamento);
    }

    private void enviarRequestsParaServicosTerceiros(Usuario usuario, TentativaPagamento tentativaPagamento) {
        String urlNotaFiscal = "https://nota-fiscal-fake.com";
        String urlRankVendedor = "https://rank-vendedor-fake.com";

        RestTemplate restTemplate = new RestTemplate();


        LinkedMultiValueMap bodyNotaFiscal = new LinkedMultiValueMap();
        bodyNotaFiscal.add("compra", tentativaPagamento.getCompra().getId());
        bodyNotaFiscal.add("usuario", usuario.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntityNotaFiscal = new HttpEntity(bodyNotaFiscal,headers);
        HttpEntity httpEntityRankVendedor = new HttpEntity(bodyNotaFiscal,headers);


        try {
            restTemplate.exchange(urlNotaFiscal, HttpMethod.POST,httpEntityNotaFiscal,String.class);
            restTemplate.exchange(urlRankVendedor, HttpMethod.POST,httpEntityRankVendedor,String.class);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
