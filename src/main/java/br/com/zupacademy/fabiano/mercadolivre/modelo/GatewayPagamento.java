package br.com.zupacademy.fabiano.mercadolivre.modelo;

public enum GatewayPagamento {

    PAYPAL,
    PAGSEGURO;

    private String url;
    private String nome;

    static  {
        PAYPAL.url = "paypal.com?buyerId=";
        PAGSEGURO.url = "pagseguro.com?returnId=";
        PAYPAL.nome = "paypal";
        PAGSEGURO.nome = "pagseguro";
    }

    public String getUrl(Long id) {
        return this.url + id.toString() + "&redirectUrl=pagamento-" + this.nome;
    }
}
