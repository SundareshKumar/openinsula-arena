CREATE TABLE DNE_GU_PAISES (
  sigla_pais_1 varchar(2) DEFAULT '' NOT NULL,
  sigla_pais_2 varchar(3) DEFAULT '' NOT NULL,
  nome_pais_portugues varchar(72) DEFAULT '' NOT NULL,
  nome_pais_ingles varchar(72) DEFAULT '' NOT NULL,
  nome_pais_frances varchar(72) DEFAULT '' NOT NULL,
  abrev_pais_portugues_ect varchar(72) DEFAULT '' NOT NULL,
  PRIMARY KEY (sigla_pais_1)
);

CREATE TABLE DNE_GU_UNIDADES_FEDERACAO (
  sigla_uf varchar(2) DEFAULT '' NOT NULL,
  chave_uf_dne decimal(2,0) DEFAULT 0 NOT NULL,
  sigla_pais_1 varchar(2) DEFAULT '' NOT NULL,
  nome_oficial_uf varchar(72) DEFAULT '' NOT NULL,
  abrev_uf_rec_ect varchar(36) DEFAULT '' NOT NULL,
  PRIMARY KEY (sigla_uf),
  FOREIGN KEY (sigla_pais_1) REFERENCES DNE_GU_PAISES (sigla_pais_1)
);

CREATE TABLE DNE_GU_LOCALIDADES (
  chave_loc_dne decimal(8,0) DEFAULT 0 NOT NULL,
  sigla_uf varchar(2) DEFAULT '' NOT NULL,
  nome_ofi_localidade varchar(72) DEFAULT '' NOT NULL,
  cep_localidade decimal(8,0) DEFAULT 0,
  abrev_loc_rec_ect varchar(36) DEFAULT '' NOT NULL,
  tipo_localidade varchar(1) DEFAULT '' NOT NULL,
  situacao_localidade varchar(1) DEFAULT '' NOT NULL,
  chave_sub_loc_dne decimal(8,0) DEFAULT 0,
  sigla_dr_ect_loc varchar(3) DEFAULT '' NOT NULL,
  PRIMARY KEY (chave_loc_dne),
  FOREIGN KEY (sigla_uf) REFERENCES DNE_GU_UNIDADES_FEDERACAO (sigla_uf)
);

CREATE INDEX DNE_GU_LOCALIDADES_CEP_IDX on DNE_GU_LOCALIDADES (cep_localidade);
CREATE INDEX DNE_GU_LOCALIDADES_BUSCA_IDX on DNE_GU_LOCALIDADES (nome_ofi_localidade,sigla_uf);

CREATE TABLE DNE_GU_BAIRROS (
  chave_bai_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_loc_dne decimal(8,0) DEFAULT 0 NOT NULL,
  sigla_uf_bai varchar(2) DEFAULT '' NOT NULL,
  nome_ofi_bai varchar(72) DEFAULT '' NOT NULL,
  abrev_bai_rec_ect varchar(36) DEFAULT '' NOT NULL,
  PRIMARY KEY (chave_bai_dne),
  FOREIGN KEY (chave_loc_dne) REFERENCES DNE_GU_LOCALIDADES (chave_loc_dne),
  FOREIGN KEY (sigla_uf_bai) REFERENCES DNE_GU_UNIDADES_FEDERACAO (sigla_uf)
);

CREATE TABLE DNE_GU_CAIXAS_POSTAIS_COMUNIT (
  chave_cx_postal_comu decimal(8,0) DEFAULT 0 NOT NULL,
  sigla_uf varchar(2) DEFAULT '' NOT NULL,
  chave_loc_dne decimal(8,0) DEFAULT 0 NOT NULL,
  cep_pt_cx_postal_comu varchar(8) DEFAULT '' NOT NULL,
  nome_pt_cx_postal_comu varchar(72) DEFAULT '' NOT NULL,
  end_pt_cx_postal_comu varchar(72) DEFAULT '' NOT NULL,
  num_ini_cx_postal_comu decimal(6,0) DEFAULT 0 NOT NULL,
  num_fim_cx_postal_comu decimal(6,0) DEFAULT 0 NOT NULL,
  area_abran_cx_postal_comu varchar(72) DEFAULT '' NOT NULL,
  PRIMARY KEY  (chave_cx_postal_comu),
  FOREIGN KEY (chave_loc_dne) REFERENCES DNE_GU_LOCALIDADES (chave_loc_dne)
);

CREATE INDEX DNE_GU_CAIXAS_POSTAIS_COMUNIT_CEP_IDX on DNE_GU_CAIXAS_POSTAIS_COMUNIT (cep_pt_cx_postal_comu);
CREATE INDEX DNE_GU_CAIXAS_POSTAIS_COMUNIT_BUSCA_IDX on DNE_GU_CAIXAS_POSTAIS_COMUNIT (end_pt_cx_postal_comu,sigla_uf);

CREATE TABLE DNE_GU_LOGRADOUROS (
  chave_logradouro_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_loc_dne decimal(8,0) DEFAULT 0 NOT NULL,
  sigla_uf varchar(2) DEFAULT '' NOT NULL,
  chave_bai_ini_dne decimal(8,0) NOT NULL,
  chave_bai_fim_dne decimal(8,0),
  tipo_ofi_logradouro varchar(26) DEFAULT '' NOT NULL,
  preposicao varchar(3) DEFAULT '' NOT NULL,
  tit_pat_ofi_logradouro varchar(72) DEFAULT '' NOT NULL,
  nome_ofi_logradouro varchar(72) DEFAULT '' NOT NULL,
  abrev_log_rec_ect varchar(36) DEFAULT '' NOT NULL,
  info_adicional varchar(36) DEFAULT '' NOT NULL,
  cep_logradouro decimal(8,0) DEFAULT 0 NOT NULL,
  ind_exis_gu_log varchar(1) DEFAULT '' NOT NULL,
  PRIMARY KEY  (chave_logradouro_dne),
  FOREIGN KEY (chave_loc_dne) REFERENCES DNE_GU_LOCALIDADES (chave_loc_dne),
  FOREIGN KEY (chave_bai_ini_dne) REFERENCES DNE_GU_BAIRROS (chave_bai_dne),
  FOREIGN KEY (chave_bai_fim_dne) REFERENCES DNE_GU_BAIRROS (chave_bai_dne)
);

CREATE INDEX DNE_GU_LOGRADOUROS_CEP_IDX on DNE_GU_LOGRADOUROS (cep_logradouro);
CREATE INDEX DNE_GU_LOGRADOUROS_BUSCA_IDX on DNE_GU_LOGRADOUROS (nome_ofi_logradouro,sigla_uf);

CREATE TABLE DNE_GU_LOGRADOUROS_SEC (
  chave_secc_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_logradouro_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_bai_dne decimal(8,0) DEFAULT 0 NOT NULL,
  num_ini_trecho decimal(11,0) DEFAULT 0 NOT NULL,
  num_fim_trecho decimal(11,0) DEFAULT 0 NOT NULL,
  ident_paridade varchar(1) DEFAULT '' NOT NULL,
  cep_sec varchar(8) DEFAULT '' NOT NULL,
  PRIMARY KEY (chave_secc_dne),
  FOREIGN KEY (chave_logradouro_dne) REFERENCES DNE_GU_LOGRADOUROS (chave_logradouro_dne),
  FOREIGN KEY (chave_bai_dne) REFERENCES DNE_GU_BAIRROS (chave_bai_dne)
);

CREATE INDEX DNE_GU_LOGRADOUROS_SEC_CEP_IDX on DNE_GU_LOGRADOUROS_SEC (cep_sec);
CREATE INDEX DNE_GU_LOGRADOUROS_SEC_BUSCA_IDX on DNE_GU_LOGRADOUROS_SEC (num_ini_trecho,num_fim_trecho);

CREATE TABLE DNE_GU_LOGRADOUROS_NUM_LOTE (
  chave_lot_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_logradouro_dne decimal(8,0) DEFAULT 0 NOT NULL,
  num_lot_dne varchar(11) DEFAULT '' NOT NULL,
  cep_lote decimal(8,0) DEFAULT 0 NOT NULL,
  PRIMARY KEY  (chave_lot_dne),
  FOREIGN KEY (chave_logradouro_dne) REFERENCES DNE_GU_LOGRADOUROS (chave_logradouro_dne)
);

CREATE INDEX DNE_GU_LOGRADOUROS_NUM_LOTE_CEP_IDX on DNE_GU_LOGRADOUROS_NUM_LOTE (cep_lote);
CREATE INDEX DNE_GU_LOGRADOUROS_NUM_LOTE_BUSCA_IDX on DNE_GU_LOGRADOUROS_NUM_LOTE (num_lot_dne);

CREATE TABLE DNE_GU_LOGRADOUROS_COMPL1 (
  chave_compl1_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_lot_dne decimal(8,0) DEFAULT 0 NOT NULL,
  num_lot_dne varchar(11) DEFAULT '',
  nome_compl1 varchar(36) DEFAULT '' NOT NULL,
  num_letra_compl1 varchar(11) DEFAULT '' NOT NULL,
  cep_compl1 decimal(8,0) DEFAULT 0 NOT NULL,
  PRIMARY KEY  (chave_compl1_dne),
  FOREIGN KEY (chave_lot_dne) REFERENCES DNE_GU_LOGRADOUROS_NUM_LOTE (chave_lot_dne)
);

CREATE INDEX DNE_GU_LOGRADOUROS_COMPL1_CEP_IDX on DNE_GU_LOGRADOUROS_COMPL1 (cep_compl1);
CREATE INDEX DNE_GU_LOGRADOUROS_COMPL1_BUSCA_IDX on DNE_GU_LOGRADOUROS_COMPL1 (num_lot_dne,nome_compl1,num_letra_compl1);

CREATE TABLE DNE_GU_GRANDES_USUARIOS (
  chave_gu_dne decimal(8,0) DEFAULT 0 NOT NULL,
  sigla_uf varchar(2) DEFAULT '' NOT NULL,
  chave_loc_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_bai_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_logradouro_dne decimal(8,0) DEFAULT 0 NOT NULL,
  nome_ofi_gu varchar(72) DEFAULT '' NOT NULL,
  cep_gu decimal(8,0) DEFAULT 0 NOT NULL,
  abrev_gu_rec_ect varchar(36) DEFAULT '' NOT NULL,
  tipo_ofi_logradouro varchar(72) DEFAULT '' NOT NULL,
  preposicao varchar(3) DEFAULT '' NOT NULL,
  tit_pat_ofi_log varchar(72) DEFAULT '' NOT NULL,
  nome_ofi_log varchar(72) DEFAULT '' NOT NULL,
  num_lote varchar(11) DEFAULT '',
  nome_compl1 varchar(36) DEFAULT '' NOT NULL,
  num_letr_compl1 varchar(11) DEFAULT '' NOT NULL,
  nome_compl2 varchar(36) DEFAULT '' NOT NULL,
  num_letr_compl2 varchar(11) DEFAULT '' NOT NULL,
  tipo_ofi_unid_ocup varchar(36) DEFAULT '' NOT NULL,
  num_letr_unid_ocup varchar(36) DEFAULT '' NOT NULL,
  PRIMARY KEY  (chave_gu_dne),
  FOREIGN KEY (chave_loc_dne) REFERENCES DNE_GU_LOCALIDADES (chave_loc_dne),
  FOREIGN KEY (chave_logradouro_dne) REFERENCES DNE_GU_LOGRADOUROS (chave_logradouro_dne),
  FOREIGN KEY (chave_bai_dne) REFERENCES DNE_GU_BAIRROS (chave_bai_dne)
);

CREATE INDEX DNE_GU_GRANDES_USUARIOS_CEP_IDX on DNE_GU_GRANDES_USUARIOS (cep_gu);
CREATE INDEX DNE_GU_GRANDES_USUARIOS_BUSCA_IDX on DNE_GU_GRANDES_USUARIOS (nome_ofi_log,num_lote,nome_compl1,num_letr_compl1,sigla_uf);

CREATE TABLE DNE_GU_UNIDADES_OPERACIONAIS (
  chave_unid_oper_dne char(18) DEFAULT '' NOT NULL,
  sigla_uf varchar(2) DEFAULT '' NOT NULL,
  chave_loc_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_bai_dne decimal(8,0) DEFAULT 0 NOT NULL,
  chave_logradouro_dne decimal(8,0) DEFAULT 0 NOT NULL,
  tipo_unid_oper varchar(72) DEFAULT '' NOT NULL,
  cep_unid_oper decimal(8,0) DEFAULT 0 NOT NULL,
  nome_ofi_unid_oper varchar(72) DEFAULT '' NOT NULL,
  abrev_unid_oper_rec_ect varchar(36) DEFAULT '' NOT NULL,
  tipo_num_cx_postal_1 varchar(1) DEFAULT '',
  num_ini_cx_postal_1 decimal(7,0) DEFAULT 0,
  num_fim_cx_postal_1 decimal(7,0) DEFAULT 0,
  tipo_num_cx_postal_2 varchar(1) DEFAULT '',
  num_ini_cx_postal_2 decimal(7,0) DEFAULT 0,
  num_fim_cx_postal_2 decimal(7,0) DEFAULT 0,
  tipo_num_cx_postal_3 varchar(1) DEFAULT '',
  num_ini_cx_postal_3 decimal(7,0) DEFAULT 0,
  num_fim_cx_postal_3 decimal(7,0) DEFAULT 0,
  tipo_ofi_log varchar(72) DEFAULT '' NOT NULL,
  preposicao varchar(3) DEFAULT '' NOT NULL,
  tit_pat_ofi_log varchar(72) DEFAULT '' NOT NULL,
  num_lote varchar(11) DEFAULT '',
  nome_compl1 varchar(36) DEFAULT '' NOT NULL,
  num_let_compl1 varchar(11) DEFAULT '' NOT NULL,
  nome_compl2 varchar(36) DEFAULT '' NOT NULL,
  num_let_compl2 varchar(11) DEFAULT '' NOT NULL,
  tipo_ofi_unid_ocup varchar(36) DEFAULT '' NOT NULL,
  num_let_unid_ocup varchar(36) DEFAULT '' NOT NULL,
  PRIMARY KEY (chave_unid_oper_dne),
  FOREIGN KEY (chave_loc_dne) REFERENCES DNE_GU_LOCALIDADES (chave_loc_dne)
);

CREATE INDEX DNE_GU_UNIDADES_OPERACIONAIS_CEP_IDX on DNE_GU_UNIDADES_OPERACIONAIS (cep_unid_oper);
CREATE INDEX DNE_GU_UNIDADES_OPERACIONAIS_BUSCA_IDX on DNE_GU_UNIDADES_OPERACIONAIS (num_lote,nome_compl1,num_let_compl1,sigla_uf);
