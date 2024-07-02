CREATE VIEW view_mat_LMD  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "Display Label", 
	 T_CODE_02 "Site",
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
	 end as "ClusterHP", 
	  CASE
	 	WHEN I_STATUS_03 = 0 THEN 'non'
	 	WHEN I_STATUS_03 = 1 THEN 'oui'
	 	ELSE null
	 end as "Verif", 
	 T_INFO_05 "OS", 
	 T_INFO_06 "Version",
	 T_INFO_04 "MotDePasse", 
	 T_INFO_07 "VersionNoyau",
	 T_INFO_08 "TypeServer",
	 I_TYPE_04 "Architecture"
	 
FROM ta_mat_material tmm 
WHERE i_type_01 =51;


