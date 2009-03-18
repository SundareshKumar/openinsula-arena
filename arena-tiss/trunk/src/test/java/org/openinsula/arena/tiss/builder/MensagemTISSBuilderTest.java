package org.openinsula.arena.tiss.builder;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao;
import br.gov.ans.padroes.tiss.schemas.CtBeneficiario;
import br.gov.ans.padroes.tiss.schemas.CtCabecalhoGuia;
import br.gov.ans.padroes.tiss.schemas.CtContratado;
import br.gov.ans.padroes.tiss.schemas.CtItemSolicitacao;
import br.gov.ans.padroes.tiss.schemas.CtTabela;
import br.gov.ans.padroes.tiss.schemas.MensagemTISS;
import br.gov.ans.padroes.tiss.schemas.ObjectFactory;
import br.gov.ans.padroes.tiss.schemas.StTipoTransacao;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.Destino;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.IdentificacaoTransacao;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.Origem;
import br.gov.ans.padroes.tiss.schemas.MensagemTISS.Epilogo;
import br.gov.ans.padroes.tiss.schemas.MensagemTISS.OperadoraParaPrestador;
import br.gov.ans.padroes.tiss.schemas.MensagemTISS.OperadoraParaPrestador.AutorizacaoServico;

public class MensagemTISSBuilderTest {

	@Test
	public void testBuild() throws Exception {
		IdentificacaoTransacao identificacaoTransacao = new IdentificacaoTransacaoBuilder(
				StTipoTransacao.SOLICITACAO_PROCEDIMENTOS, BigInteger.valueOf(123L), new GregorianCalendar(2009,
						Calendar.MARCH, 11, 14, 18, 22).getTime()).build();
		Origem origem = new OrigemDestinoBuilder().registroANS("371254").buildOrigem();
		Destino destino = new OrigemDestinoBuilder().codigoPrestadorNaOperadora("288900").buildDestino();
		CabecalhoTransacao cabecalhoTransacao = new CabecalhoTransacaoBuilder(identificacaoTransacao, origem, destino).build();

		CtCabecalhoGuia cabecalhoGuia = new CtCabecalhoGuiaBuilder("371254", new GregorianCalendar(2009,
						Calendar.MARCH, 11, 14, 18, 22).getTime(), "1234").build();
		CtBeneficiario beneficiario = new CtBeneficiarioBuilder("00970008849115", "USUARIO TESTE", "SEM PLANO").build();
		CtContratado contratado = new CtContratadoBuilder("288900", "PRESTADOR TESTE").build();
		AutorizacaoServicoBuilder autorizacaoServicoBuilder = new AutorizacaoServicoBuilder(cabecalhoGuia, beneficiario).prestadorAutorizado(contratado).statusSolicitacao(StatusSolicitacao.AUTORIZADO);
		
		CtTabela tabela = new CtTabelaBuilder("101010112", TipoTabela.AMB_90).descricao("ELETROCARDIOGRAMA").build();
		CtItemSolicitacao itemSolicitacao = new CtItemSolicitacaoBuilder(tabela, 1, StatusSolicitacao.AUTORIZADO).quantidadeAutorizada(1).observacao("VERDE - APROVADO").build();
		
		AutorizacaoServico autorizacaoServico = autorizacaoServicoBuilder.procedimento(itemSolicitacao).build();

		OperadoraParaPrestador operadoraParaPrestador = new OperadoraParaPrestador();
		operadoraParaPrestador.getAutorizacaoServico().add(autorizacaoServico);

		MensagemTISS mensagemTISS = new MensagemTISS();
		mensagemTISS.setCabecalho(cabecalhoTransacao);
		mensagemTISS.setOperadoraParaPrestador(operadoraParaPrestador);
		
		Epilogo epilogo = new Epilogo();
		epilogo.setHash("ABCDEF");
		
		mensagemTISS.setEpilogo(epilogo);

		JAXBContext context = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller
				.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
						"http://www.ans.gov.br/padroes/tiss/schemas http://www.ans.gov.br/padroes/tiss/schemas/tissV2_02_01.xsd");

		marshaller.marshal(mensagemTISS, System.out);
	}

}