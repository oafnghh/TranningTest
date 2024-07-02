DROP TABLE IF EXISTS TA_NSO_GROUP_HISTORY;
DROP TABLE IF EXISTS TA_NSO_GROUP_MEMBER;
DROP TABLE IF EXISTS TA_NSO_GROUP;


CREATE TABLE TA_NSO_GROUP (
  I_ID 				SERIAL PRIMARY KEY,
  T_Ref 			varchar(100) DEFAULT NULL,
  T_Name 			varchar(200) DEFAULT NULL,
  
  T_Info_01 		text DEFAULT NULL,
  T_Info_02 		text DEFAULT NULL,
--  T_Info_03 		text DEFAULT NULL,
--  T_Info_04 		text DEFAULT NULL,
--  T_Info_05 		text DEFAULT NULL,
  
  D_Date_01 		timestamp DEFAULT NULL,
  D_Date_02 		timestamp DEFAULT NULL,
  
  I_Status_01 		integer DEFAULT NULL,
  I_Status_02 		integer DEFAULT NULL,
  
  I_Type_01 		integer DEFAULT NULL,
  I_Type_02 		integer DEFAULT NULL,
  
  I_Per_Manager 	integer DEFAULT NULL,
  I_Aut_User 		integer DEFAULT NULL
) ;

CREATE INDEX CONCURRENTLY idx_TNGRO_00 ON TA_NSO_GROUP USING btree(T_Ref);
CREATE INDEX CONCURRENTLY idx_TNGRO_01 ON TA_NSO_GROUP USING btree(T_Name);
CREATE INDEX CONCURRENTLY idx_TNGRO_02 ON TA_NSO_GROUP USING btree(I_Type_01);
CREATE INDEX CONCURRENTLY idx_TNGRO_03 ON TA_NSO_GROUP USING btree(I_Type_02);
CREATE INDEX CONCURRENTLY idx_TNGRO_04 ON TA_NSO_GROUP USING btree(I_Per_Manager);

COMMENT on column TA_NSO_GROUP.I_Status_01  is 'status by admin';
COMMENT on column TA_NSO_GROUP.I_Status_02  is '1: Publish, 2: Private, 0: Desactivate';
COMMENT on column TA_NSO_GROUP.D_Date_01 	is 'date creation';
COMMENT on column TA_NSO_GROUP.D_Date_02 	is 'date mod';
COMMENT on column TA_NSO_GROUP.I_Per_Manager is 'Per_Person moral manager';
COMMENT on column TA_NSO_GROUP.I_Aut_User 	is 'user creator';

CREATE TABLE TA_NSO_GROUP_MEMBER (
  I_ID 			SERIAL PRIMARY KEY,
  I_Nso_Group 	integer DEFAULT NULL,
  I_Aut_User 	integer DEFAULT NULL,
  I_Status 		integer DEFAULT NULL,
  I_Type 		integer DEFAULT NULL,
  T_Info_01 	text DEFAULT NULL,
  T_Info_02 	text DEFAULT NULL,
  D_Date_01 	timestamp DEFAULT NULL,
  D_Date_02 	timestamp DEFAULT NULL
) ;
CREATE INDEX CONCURRENTLY idx_TNGME_01 ON TA_NSO_GROUP_MEMBER USING btree(I_Nso_Group);
CREATE INDEX CONCURRENTLY idx_TNGME_02 ON TA_NSO_GROUP_MEMBER USING btree(I_Aut_User);

COMMENT on column TA_NSO_GROUP_MEMBER.I_Status  	is '0: waiting, 1: accept, 10: Desactivate';
COMMENT on column TA_NSO_GROUP_MEMBER.I_Type  		is '1: adm, 2: member lev 1, 2: member lev 2';
COMMENT on column TA_NSO_GROUP_MEMBER.D_Date_01 	is 'date creation';
COMMENT on column TA_NSO_GROUP_MEMBER.D_Date_02 	is 'date mod';


CREATE TABLE TA_NSO_GROUP_HISTORY (
  I_ID 				SERIAL PRIMARY KEY,
  I_Nso_Group 		integer NOT NULL,
  I_Msg_Message 	integer NOT NULL,
  I_Aut_User 		integer NOT NULL,
  I_Status 			integer NOT NULL,
  T_Info_01 		text DEFAULT NULL,
  T_Info_02 		text DEFAULT NULL,
  D_Date 			timestamp DEFAULT NULL
) ;

CREATE INDEX CONCURRENTLY idx_TNGHI_01 ON TA_NSO_GROUP_HISTORY USING btree(I_Nso_Group);
CREATE INDEX CONCURRENTLY idx_TNGHI_02 ON TA_NSO_GROUP_HISTORY USING btree(I_Aut_User);

