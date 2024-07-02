CREATE VIEW view_mat_LMP  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "Display Label", 
	 T_CODE_01 "Applications",
	 T_CODE_02 "Localisation",
	 T_CODE_03 "Alias",
	 T_CODE_04 "ExtraFlags",
	 T_CODE_05 "Requetes",
	 
	 CASE
	 	WHEN I_STATUS_01 = 0 THEN 'en attente'
	 	WHEN I_STATUS_01 = 1 THEN 'en exploitation'
	 	WHEN I_STATUS_01 = 2 THEN 'réformé'
	 	WHEN I_STATUS_01 = 3 THEN 'réservé'
	 	WHEN I_STATUS_01 = 5 THEN 'en cours de réaffectation'
	 	WHEN I_STATUS_01 = 10 THEN 'supprimé'
	 	ELSE null
	 end as "Etat", 
	 CASE
	 	WHEN I_STATUS_02 = 0 THEN 'non'
	 	WHEN I_STATUS_02 = 1 THEN 'oui'
	 	ELSE null
	 end as "Criticite", 
	  CASE
	 	WHEN I_STATUS_03 = 0 THEN 'non'
	 	WHEN I_STATUS_03 = 1 THEN 'oui'
	 	ELSE null
	 end as "Exploitabilite", 
	  CASE
	 	WHEN I_STATUS_04 = 0 THEN 'non'
	 	WHEN I_STATUS_04 = 1 THEN 'oui'
	 	ELSE null
	 end as "Astreinte", 
	 T_INFO_05 "OS", 
	 T_INFO_06 "Version",
	 T_INFO_04 "ClefSSH", 
	 T_INFO_07 "PDS",
	 T_INFO_08 "TypeServer"
	 
FROM ta_mat_material tmm 
WHERE i_type_01 =52;

		
			
	