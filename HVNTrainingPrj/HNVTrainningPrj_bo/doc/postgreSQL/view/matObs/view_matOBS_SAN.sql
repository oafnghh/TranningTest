CREATE VIEW view_mat_obs_SAN  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "Baie", 
	 T_INFO_01 "Constructeur",
	 T_INFO_02 "N/S",
	 T_INFO_03 "Type",
	 T_CODE_02 "Site",
	 T_NAME_02 "Serveur"
FROM ta_mat_material_obs tmm 
WHERE i_type_01 =1100;