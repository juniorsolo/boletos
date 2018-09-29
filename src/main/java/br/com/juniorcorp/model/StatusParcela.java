package br.com.juniorcorp.model;

public enum StatusParcela {
	PAGA("Paga"),
	AGUARDANDO_PAGAMENTO("Aguardando pagamento"),
	EM_ATRASO("Em atraso"),
	CANCELADA("Cancelada");
	
	private String descricao;	

    StatusParcela(String status) {
		descricao = status;
	}
	
    public String getDescricao() {
    	return descricao;
    }
	
}
