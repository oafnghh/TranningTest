-- --------------------------------------------------------------------------
-- --------------------------------------------------------------------------
-- --------------------------------------------------------------------------
DROP TABLE IF EXISTS TA_AUT_AUTH_SERVICE;
DROP TABLE IF EXISTS TA_AUT_HISTORY;
DROP TABLE IF EXISTS TA_AUT_AUTH_USER;
DROP TABLE IF EXISTS TA_AUT_RIGHT;
DROP TABLE IF EXISTS TA_AUT_ROLE;
DROP TABLE IF EXISTS TA_AUT_USER;

CREATE TABLE TA_AUT_RIGHT (
  I_ID 			integer PRIMARY KEY,
  T_Name 		varchar(200) NOT NULL,  
  T_Info_01 	varchar(500) DEFAULT NULL,
  T_Info_02 	varchar(200) DEFAULT NULL
);
CREATE INDEX CONCURRENTLY idx_TARIG_01 ON TA_AUT_RIGHT USING btree (T_Name);
COMMENT on column TA_AUT_RIGHT.I_ID 		is 'right: get, mod, del, new, print, report...';
COMMENT on column TA_AUT_RIGHT.T_Info_01 	is 'description';
COMMENT on column TA_AUT_RIGHT.T_Info_02 	is 'group';


CREATE TABLE TA_AUT_ROLE (
  I_ID 			integer PRIMARY KEY,
  I_Status 		integer NOT NULL,  
  T_Name 		varchar(200) NOT NULL,   
  T_Info_01 	varchar(500) DEFAULT NULL  ,
  T_Info_02 	varchar(200) DEFAULT NULL, 
  T_Aut_Right 	text DEFAULT NULL 
) ;
CREATE INDEX CONCURRENTLY idx_TAROL_01 ON TA_AUT_ROLE USING btree (T_Name);
COMMENT on column TA_AUT_ROLE.T_Info_01 	is 'description';
COMMENT on column TA_AUT_ROLE.T_Info_02 	is 'group';
COMMENT on column TA_AUT_ROLE.T_Aut_Right 	is 'right ids, ex: 12001, 12003';


CREATE TABLE TA_AUT_USER (
  I_ID 	  	SERIAL PRIMARY KEY,
  I_Status  integer DEFAULT NULL,
  
  I_Type_01 integer DEFAULT NULL ,
  I_Type_02 integer DEFAULT NULL ,
   
  T_Login_01 varchar(100)  DEFAULT NULL,
  T_Login_02 varchar(100)  DEFAULT NULL,
  T_Login_03 varchar(100)  DEFAULT NULL,
  
  T_Pass_01 varchar(1000)  DEFAULT NULL,
  T_Pass_02 varchar(1000)  DEFAULT NULL,
  T_Pass_03 varchar(1000)  DEFAULT NULL,
  
  T_Info_01 text  DEFAULT NULL ,
  T_Info_02 text  DEFAULT NULL ,
  T_Info_03 text  DEFAULT NULL,
  T_Info_04 text  DEFAULT NULL,
  T_Info_05 text  DEFAULT NULL,
  
  T_Info_06 text  DEFAULT NULL,
  T_Info_07 text  DEFAULT NULL,
  T_Info_08 text  DEFAULT NULL,
  T_Info_09 text  DEFAULT NULL,
  T_Info_10 text  DEFAULT NULL,
  
  D_Date_01 timestamp DEFAULT NULL ,
  D_Date_02 timestamp DEFAULT NULL ,
  D_Date_03 timestamp DEFAULT NULL,
  D_Date_04 timestamp DEFAULT NULL,
  
  I_Aut_User_01 integer DEFAULT NULL ,
  I_Aut_User_02 integer DEFAULT NULL ,
  I_Aut_User_03 integer DEFAULT NULL ,
  
  I_Per_Person  integer DEFAULT NULL ,
  I_Per_Manager integer DEFAULT NULL
);
CREATE INDEX CONCURRENTLY idx_TUSER_01 ON TA_AUT_USER USING btree (T_Login_01);
CREATE INDEX CONCURRENTLY idx_TUSER_02 ON TA_AUT_USER USING btree (T_Login_02);
CREATE INDEX CONCURRENTLY idx_TUSER_03 ON TA_AUT_USER USING btree (T_Login_03);
COMMENT on column TA_AUT_USER.I_Type_01 	is 'type adm, agent, visitor, member....';
COMMENT on column TA_AUT_USER.I_Type_02 	is 'sub type';
COMMENT on column TA_AUT_USER.T_Info_01 	is 'email';
COMMENT on column TA_AUT_USER.T_Info_02 	is 'tel';
COMMENT on column TA_AUT_USER.D_Date_01 	is 'dt new';
COMMENT on column TA_AUT_USER.D_Date_02 	is 'dt mod';
COMMENT on column TA_AUT_USER.D_Date_03 	is 'dt bg limit if need validation';
COMMENT on column TA_AUT_USER.D_Date_04 	is 'dt end limit if need validation';
COMMENT on column TA_AUT_USER.I_Aut_User_01 	is 'id user new';
COMMENT on column TA_AUT_USER.I_Aut_User_02 	is 'id user mod';
COMMENT on column TA_AUT_USER.I_Aut_User_03 	is 'id user sup';
COMMENT on column TA_AUT_USER.I_Per_Person 		is 'person info';



CREATE TABLE TA_AUT_AUTH_USER (
  I_ID 			SERIAL PRIMARY KEY,
  I_Aut_User 	integer DEFAULT NULL,
  I_Aut_Role 	integer DEFAULT NULL,
  I_Status 		integer DEFAULT NULL,
  D_Date_01 	timestamp DEFAULT NULL,
  D_Date_02 	timestamp DEFAULT NULL,
  T_Aut_Right 	text DEFAULT NULL,
  CONSTRAINT FK_TAAUS_02 FOREIGN KEY (I_Aut_Role) REFERENCES TA_AUT_ROLE (I_ID),
  CONSTRAINT FK_TAAUS_03 FOREIGN KEY (I_Aut_User) REFERENCES TA_AUT_USER (I_ID)
);

CREATE INDEX CONCURRENTLY idx_TAAUS_01 ON TA_AUT_AUTH_USER USING btree (I_Aut_User);
CREATE INDEX CONCURRENTLY idx_TAAUS_02 ON TA_AUT_AUTH_USER USING btree (I_Aut_Role);
COMMENT on column TA_AUT_AUTH_USER.D_Date_01 	is 'dt begin';
COMMENT on column TA_AUT_AUTH_USER.D_Date_02 	is 'dt end';
COMMENT on column TA_AUT_AUTH_USER.T_Aut_Right 	is 'right ids, ex: 12001, 12003/copy from AUT_ROLE';




CREATE TABLE TA_AUT_AUTH_SERVICE (
  I_ID 			SERIAL PRIMARY KEY,
  T_Info_01 	text NOT NULL,
  T_Info_02 	text NOT NULL,
  I_Type_01 	integer DEFAULT NULL ,
  I_Type_02 	integer DEFAULT NULL ,
  I_Status 		integer DEFAULT NULL ,
  D_Date_01 	timestamp DEFAULT NULL,
  D_Date_02 	timestamp DEFAULT NULL,
  T_Aut_Role 	text DEFAULT NULL ,
  T_Aut_Right 	text DEFAULT NULL
) ;
CREATE INDEX CONCURRENTLY idx_TAASE_01 ON TA_AUT_AUTH_SERVICE USING btree (T_Info_01);
CREATE INDEX CONCURRENTLY idx_TAASE_02 ON TA_AUT_AUTH_SERVICE USING btree (T_Info_02);
COMMENT on column TA_AUT_AUTH_SERVICE.T_Info_01 	is 'serviceClass.serviceName';
COMMENT on column TA_AUT_AUTH_SERVICE.T_Info_02 	is 'description';
COMMENT on column TA_AUT_AUTH_SERVICE.D_Date_01 	is 'dt begin';
COMMENT on column TA_AUT_AUTH_SERVICE.D_Date_02 	is 'dt end';
COMMENT on column TA_AUT_AUTH_SERVICE.T_Aut_Role 	is 'role ids,ex: 100, 200 & 300, 102 & 400';
COMMENT on column TA_AUT_AUTH_SERVICE.T_Aut_Right 	is 'right ids, ex: 12001, 12003 & 12004';

CREATE TABLE TA_AUT_HISTORY (
  I_ID 			SERIAL 	PRIMARY KEY,
  I_Aut_User 	integer NOT NULL,
  I_Type 		integer NOT NULL,
  D_Date 		timestamp NOT NULL,
  T_Val 		text DEFAULT NULL,
  CONSTRAINT fk_TACHI_01 FOREIGN KEY (I_Aut_User) REFERENCES TA_AUT_USER (I_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;
CREATE INDEX CONCURRENTLY idx_TAHIS_01 ON TA_AUT_HISTORY USING btree (I_Aut_User);
CREATE INDEX CONCURRENTLY idx_TAHIS_02 ON TA_AUT_HISTORY USING btree (I_Type);
COMMENT on column TA_AUT_HISTORY.T_Val 	is 'IP/other info';

-- --------------------------------------------------------------------------
-- --------------------------------------------------------------------------
-- --------------------------------------------------------------------------
--
-- Table structure for table TA_PER_PERSON
--

DROP TABLE IF EXISTS TA_PER_PERSON;
CREATE TABLE TA_PER_PERSON (
  I_ID SERIAL PRIMARY KEY,
  
  T_Name_01 varchar(200)  NOT 	  NULL ,
  T_Name_02 varchar(200)  DEFAULT NULL ,
  T_Name_03 varchar(200)  DEFAULT NULL ,
  T_Name_04 varchar(200)  DEFAULT NULL ,
  T_Name_05 varchar(200)  DEFAULT NULL ,
  
  I_Status_01  integer DEFAULT NULL ,
  I_Status_02  integer DEFAULT NULL ,
  
  I_Type_01 integer DEFAULT NULL ,
  I_Type_02 integer DEFAULT NULL ,
  I_Type_03 integer DEFAULT NULL ,
  I_Type_04 integer DEFAULT NULL ,
  I_Type_05 integer DEFAULT NULL ,
  I_Type_06 integer DEFAULT NULL ,
  I_Type_07 integer DEFAULT NULL ,
  I_Type_08 integer DEFAULT NULL ,
  I_Type_09 integer DEFAULT NULL ,
  I_Type_10 integer DEFAULT NULL ,
  
  F_Val_01 DOUBLE PRECISION DEFAULT NULL ,
  F_Val_02 DOUBLE PRECISION DEFAULT NULL ,
  F_Val_03 DOUBLE PRECISION DEFAULT NULL ,
  F_Val_04 DOUBLE PRECISION DEFAULT NULL ,
  F_Val_05 DOUBLE PRECISION DEFAULT NULL ,
  
  T_Code_01 varchar(200)  DEFAULT NULL ,
  T_Code_02 varchar(200)  DEFAULT NULL ,
  T_Code_03 varchar(200)  DEFAULT NULL ,
  T_Code_04 varchar(200)  DEFAULT NULL ,
  T_Code_05 varchar(200)  DEFAULT NULL ,
  T_Code_06 varchar(200)  DEFAULT NULL ,
  T_Code_07 varchar(200)  DEFAULT NULL ,
  T_Code_08 varchar(200)  DEFAULT NULL ,
  T_Code_09 varchar(200)  DEFAULT NULL ,
  T_Code_10 varchar(200)  DEFAULT NULL ,
  
  T_Info_01 text  DEFAULT NULL ,
  T_Info_02 text  DEFAULT NULL ,
  T_Info_03 text  DEFAULT NULL ,
  T_Info_04 text  DEFAULT NULL ,
  T_Info_05 text  DEFAULT NULL ,
  T_Info_06 text  DEFAULT NULL ,
  T_Info_07 text  DEFAULT NULL ,
  T_Info_08 text  DEFAULT NULL ,
  T_Info_09 text  DEFAULT NULL ,
  T_Info_10 text  DEFAULT NULL ,
  
  D_Date_01 timestamp DEFAULT NULL ,
  D_Date_02 timestamp DEFAULT NULL ,
  D_Date_03 timestamp DEFAULT NULL ,
  D_Date_04 timestamp DEFAULT NULL ,
  D_Date_05 timestamp DEFAULT NULL ,
  D_Date_06 timestamp DEFAULT NULL ,
  D_Date_07 timestamp DEFAULT NULL ,
  D_Date_08 timestamp DEFAULT NULL ,
  D_Date_09 timestamp DEFAULT NULL ,
  D_Date_10 timestamp DEFAULT NULL ,
  
  I_Aut_User_01 integer DEFAULT NULL ,
  I_Aut_User_02 integer DEFAULT NULL ,
  I_Per_Manager integer DEFAULT NULL 
) ;
CREATE INDEX CONCURRENTLY idx_TPERS_01 ON TA_PER_PERSON USING btree(T_Name_01);
CREATE INDEX CONCURRENTLY idx_TPERS_02 ON TA_PER_PERSON USING btree(T_Name_02);
CREATE INDEX CONCURRENTLY idx_TPERS_03 ON TA_PER_PERSON USING btree(T_Name_03);
  
CREATE INDEX CONCURRENTLY idx_TPERS_11 ON TA_PER_PERSON USING btree(T_Code_01);
CREATE INDEX CONCURRENTLY idx_TPERS_12 ON TA_PER_PERSON USING btree(T_Code_02);
CREATE INDEX CONCURRENTLY idx_TPERS_13 ON TA_PER_PERSON USING btree(T_Code_03);
CREATE INDEX CONCURRENTLY idx_TPERS_14 ON TA_PER_PERSON USING btree(T_Code_04);
CREATE INDEX CONCURRENTLY idx_TPERS_15 ON TA_PER_PERSON USING btree(T_Code_05);
  
CREATE INDEX CONCURRENTLY idx_TPERS_21 ON TA_PER_PERSON USING btree(I_Type_01);
CREATE INDEX CONCURRENTLY idx_TPERS_22 ON TA_PER_PERSON USING btree(I_Type_02);
CREATE INDEX CONCURRENTLY idx_TPERS_23 ON TA_PER_PERSON USING btree(I_Type_03);
CREATE INDEX CONCURRENTLY idx_TPERS_24 ON TA_PER_PERSON USING btree(I_Type_04);
CREATE INDEX CONCURRENTLY idx_TPERS_25 ON TA_PER_PERSON USING btree(I_Type_05);
CREATE INDEX CONCURRENTLY idx_TPERS_26 ON TA_PER_PERSON USING btree(I_Type_06);
CREATE INDEX CONCURRENTLY idx_TPERS_27 ON TA_PER_PERSON USING btree(I_Type_07);
CREATE INDEX CONCURRENTLY idx_TPERS_28 ON TA_PER_PERSON USING btree(I_Type_08);
CREATE INDEX CONCURRENTLY idx_TPERS_29 ON TA_PER_PERSON USING btree(I_Type_09);
CREATE INDEX CONCURRENTLY idx_TPERS_30 ON TA_PER_PERSON USING btree(I_Type_10);

COMMENT on column TA_PER_PERSON.T_Name_01	is 'Họ / Tên doanh nghiệp';
COMMENT on column TA_PER_PERSON.T_Name_02	is 'Tên đệm';
COMMENT on column TA_PER_PERSON.T_Name_03	is 'Tên/ Tên gọi khác của doanh nghiệp';
COMMENT on column TA_PER_PERSON.T_Name_04	is 'Tên khác';
COMMENT on column TA_PER_PERSON.T_Name_05   is 'Tên khác';
  
COMMENT on column TA_PER_PERSON.I_Status_01  is 'status by default in program';
COMMENT on column TA_PER_PERSON.I_Status_02  is 'sub status';
  
COMMENT on column TA_PER_PERSON.I_Type_01 is 'kiểu person:  100: kiểu cán bộ công nhân viên chức, 200: sinh viên, 1000: doanh nghiệp,';
COMMENT on column TA_PER_PERSON.I_Type_02 is 'kiểu phân loại: doanh nghiệp: đối tác, cung ứng, khách hàng , cán bộ: giảng viên, chuyên viên..., sinh viên: ....';
COMMENT on column TA_PER_PERSON.I_Type_03 is '0: ko phân biệt, 1: nam, 2: nữ';
COMMENT on column TA_PER_PERSON.I_Type_04 is 'tình trạng tôn giáo	: không, phật giáo, công giáo, khác';
COMMENT on column TA_PER_PERSON.I_Type_05 is 'tình trạng đảng phái: Không, Đoàn, Đảng, Khác';
COMMENT on column TA_PER_PERSON.I_Type_06 is 'tình trạng gia đình 01: kết hôn, độc thân...';
COMMENT on column TA_PER_PERSON.I_Type_07 is 'tình trạng gia đình 02: đối tượng (hộ nghèo, thương binh, liệt sĩ...)';
COMMENT on column TA_PER_PERSON.I_Type_08 is 'tình trạng tuyển sinh/tuyển dụng 01: khu vực tuyển sinh';
COMMENT on column TA_PER_PERSON.I_Type_09 is 'tình trạng tuyển sinh/tuyển dụng 02: hình thức xét tuyển';
COMMENT on column TA_PER_PERSON.I_Type_10 is 'tình trạng khác';
  
COMMENT on column TA_PER_PERSON.F_Val_01 is 'hệ số lương khởi động';
COMMENT on column TA_PER_PERSON.F_Val_02 is 'hệ số lương hiện tại';
COMMENT on column TA_PER_PERSON.F_Val_03 is 'other';
COMMENT on column TA_PER_PERSON.F_Val_04 is 'other';
COMMENT on column TA_PER_PERSON.F_Val_05 is 'other';
  
COMMENT on column TA_PER_PERSON.T_Code_01 is 'CMND, đăng ký kinh doanh';
COMMENT on column TA_PER_PERSON.T_Code_02 is 'CCCD, số đăng ký kinh doanh khác nếu có';
COMMENT on column TA_PER_PERSON.T_Code_03 is 'Số BHXH';
COMMENT on column TA_PER_PERSON.T_Code_04 is 'Mã QL nội bộ: số sinh viên, mã phòng ban';
COMMENT on column TA_PER_PERSON.T_Code_05 is 'Mã QL khác nếu có';
COMMENT on column TA_PER_PERSON.T_Code_06 is 'Mã QL khác nếu có';
COMMENT on column TA_PER_PERSON.T_Code_07 is 'Mã QL khác nếu có';
COMMENT on column TA_PER_PERSON.T_Code_08 is 'Mã QL khác nếu có';
COMMENT on column TA_PER_PERSON.T_Code_09 is 'Mã QL khác nếu có';
COMMENT on column TA_PER_PERSON.T_Code_10 is 'Mã QL khác nếu có';
  
COMMENT on column TA_PER_PERSON.T_Info_01 is 'Json Thông tin cụ thể như địa chỉ tạm trú, thông tin cha mẹ....';
COMMENT on column TA_PER_PERSON.T_Info_02 is 'Json Thông tin khác';
COMMENT on column TA_PER_PERSON.T_Info_03 is 'Json khác';
COMMENT on column TA_PER_PERSON.T_Info_04 is 'Json khác';
COMMENT on column TA_PER_PERSON.T_Info_05 is 'Json khác';
COMMENT on column TA_PER_PERSON.T_Info_06 is 'Json khác';
COMMENT on column TA_PER_PERSON.T_Info_07 is 'Json khác';
COMMENT on column TA_PER_PERSON.T_Info_08 is 'Json khác';
COMMENT on column TA_PER_PERSON.T_Info_09 is 'Json khác';
COMMENT on column TA_PER_PERSON.T_Info_10 is 'Json khác';
  
COMMENT on column TA_PER_PERSON.D_Date_01 is 'Ngày tạo';
COMMENT on column TA_PER_PERSON.D_Date_02 is 'Ngày thay đổi';
COMMENT on column TA_PER_PERSON.D_Date_03 is 'Ngày sinh';
COMMENT on column TA_PER_PERSON.D_Date_04 is 'Ngày bắt đầu (bắt đầu làm việc, học)';
COMMENT on column TA_PER_PERSON.D_Date_05 is 'Ngày kết thúc (nghỉ việc, nghỉ học)';
COMMENT on column TA_PER_PERSON.D_Date_06 is 'Ngày ...';
COMMENT on column TA_PER_PERSON.D_Date_07 is 'Ngày ...';
COMMENT on column TA_PER_PERSON.D_Date_08 is 'Ngày ...';
COMMENT on column TA_PER_PERSON.D_Date_09 is 'Ngày ...';
COMMENT on column TA_PER_PERSON.D_Date_10 is 'Ngày ...';
  
COMMENT on column TA_PER_PERSON.I_Aut_User_01 is 'Người tạo';
COMMENT on column TA_PER_PERSON.I_Aut_User_02 is 'Người thay đổi';
-- --------------------------------------------------------------------------
-- --------------------------------------------------------------------------
-- --------------------------------------------------------------------------



