CREATE VIEW view_mat_obs_TINA  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "Machine",
	 T_NAME_02 "Catalogue",
 	 CASE
	 	WHEN I_STATUS_01 = 0 THEN 'en attente'
	 	WHEN I_STATUS_01 = 1 THEN 'en exploitation'
	 	WHEN I_STATUS_01 = 2 THEN 'réformé'
	 	WHEN I_STATUS_01 = 3 THEN 'réservé'
	 	WHEN I_STATUS_01 = 5 THEN 'en cours de réaffectation'
	 	WHEN I_STATUS_01 = 10 THEN 'supprimé'
	 	ELSE null
	 end as "Etat", 
	 T_NAME_03 "Nom PF",
	 T_INFO_01 "Nature PF",
	 T_INFO_02 "Type PF",
	 T_INFO_06 "OS",
	 T_CODE_02 "Site",
	 T_INFO_03 "Mode",
	 T_INFO_05 "Environnement"
FROM ta_mat_material_obs tmm 
WHERE i_type_01 =1300;	 
