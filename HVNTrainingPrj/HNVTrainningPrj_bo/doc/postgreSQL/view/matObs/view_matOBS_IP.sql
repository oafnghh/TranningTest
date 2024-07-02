CREATE VIEW view_mat_obs_add_ip  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "IP 188.x",
	 T_INFO_01 "Nom DNS",
	 T_NAME_02 "Srv",
	 T_NAME_03 "Srv appli"
FROM ta_mat_material_obs tmm 
WHERE i_type_01 =1200;	 
