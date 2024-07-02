CREATE VIEW view_mat_obs_esx4  AS
SELECT 
	I_ID  "id",
	T_NAME_01 "VM",
	CASE
		WHEN I_STATUS_02 = 0 THEN 'powerOff'
		WHEN I_STATUS_02 = 1 THEN 'powerOn'
	end as "Powerstate",
	T_INFO_05 "Environnement",
	T_INFO_02 "Datacenter",
	T_INFO_03 "Cluster",
	T_INFO_01 "Annotation",
	T_INFO_04 "Environnment"
FROM ta_mat_material_obs tmm 
WHERE i_type_01 = 1500;