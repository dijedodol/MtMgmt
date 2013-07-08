ALTER TABLE users AUTO_INCREMENT = 0;
ALTER TABLE spbus AUTO_INCREMENT = 0;

insert into users(access_role, full_name, login_id, password_hash) values('Administrator', 0, 'adm', 'adm');
insert into users(access_role, full_name, login_id, password_hash) values('Technician', 1, 'tch', 'tch');
insert into users(access_role, full_name, login_id, password_hash) values('Supervisor', 2, 'spv', 'spv');
insert into users(access_role, full_name, login_id, password_hash) values('Supervisor Palsu', 2, 'spvpalsu', 'spvpalsu');

insert into spbus(code, address, phone, supervisor_id) values('54-801.21', 'Jalan Imam Bonjol', '0361 701111', 3);
insert into spbus(code, address, phone, supervisor_id) values('Palsu', 'Jalan Palsu', '0361 Palsu', 4);

insert into machine_models(model_id, name) values('Encore S-300.1', 'Encore S-300 6 Assy');
insert into machine_models(model_id, name) values('Encore S-300.2', 'Encore S-300 4 Assy');

insert into machine_part_types(part_id, name, default_mttf, default_mttf_threshold, mttf_by_totalizer) values('Assymeter', 'Assymeter', 0, 0, true);
insert into machine_part_types(part_id, name, default_mttf, default_mttf_threshold, mttf_by_totalizer) values('Filter', 'Filter', 30, 23, false);
insert into machine_part_types(part_id, name, default_mttf, default_mttf_threshold, mttf_by_totalizer) values('Solenoid Valve', 'Solenoid Valve', 90, 83, false);
insert into machine_part_types(part_id, name, default_mttf, default_mttf_threshold, mttf_by_totalizer) values('Nozzle', 'Nozzle', 180, 173, false);

insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Assymeter', 'Assymeter1');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Assymeter', 'Assymeter2');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Assymeter', 'Assymeter3');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Assymeter', 'Assymeter4');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Assymeter', 'Assymeter5');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Assymeter', 'Assymeter6');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Filter', 'Filter');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Solenoid Valve', 'Solenoid Valve');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.1', 'Nozzle', 'Nozzle');

insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.2', 'Assymeter', 'Assymeter1');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.2', 'Assymeter', 'Assymeter2');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.2', 'Assymeter', 'Assymeter3');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.2', 'Assymeter', 'Assymeter4');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.2', 'Filter', 'Filter');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.2', 'Solenoid Valve', 'Solenoid Valve');
insert into machine_model_parts(model_id, part_id, machine_model_part_identifier) values('Encore S-300.2', 'Nozzle', 'Nozzle');

insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.1', 'Totalizer1');
insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.1', 'Totalizer2');
insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.1', 'Totalizer3');
insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.1', 'Totalizer4');
insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.1', 'Totalizer5');
insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.1', 'Totalizer6');

insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.2', 'Totalizer1');
insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.2', 'Totalizer2');
insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.2', 'Totalizer3');
insert into machine_model_totalizers(model_id, totalizer_id) values('Encore S-300.2', 'Totalizer4');

insert into part_failure_modes(part_id, failure_mode_id, description) values('Assymeter', 'Volume BBM Tidak Stabil', 'Volume BBM yang dikeluarkan mesin tidak stabil');
insert into part_failure_modes(part_id, failure_mode_id, description) values('Assymeter', 'Tidak Mengeluarkan BBM', 'Tidak ada BBM yang dikeluarkan meskipun handle nozzle telah ditekan');
insert into part_failure_modes(part_id, failure_mode_id, description) values('Filter', 'Aliran BBM Tidak Maksimal', 'Aliran volume BBM yang dikeluarkan tidak dapat mencapai kecepatan maksimal');

insert into failure_mode_handlings(part_id, failure_mode_id, failure_mode_handling_id, description) values('Assymeter', 'Volume BBM Tidak Stabil', 'Perbaiki Bearing & Liner', 'Perbaiki Bearing & Liner');
insert into failure_mode_handlings(part_id, failure_mode_id, failure_mode_handling_id, description) values('Assymeter', 'Tidak Mengeluarkan BBM', 'Perbaiki Shaft Utama', 'Perbaiki Shaft Utama');
insert into failure_mode_handlings(part_id, failure_mode_id, failure_mode_handling_id, description) values('Filter', 'Aliran BBM Tidak Maksimal', 'Dibersihkan', 'Dibersihkan');
insert into failure_mode_handlings(part_id, failure_mode_id, failure_mode_handling_id, description) values('Filter', 'Aliran BBM Tidak Maksimal', 'Ganti Baru', 'Ganti Baru');

insert into spbu_machines(spbu_id, model_id, serial_number, machine_identifier) values(1, 'Encore S-300.1', '10001', 'Mesin1');
insert into spbu_machines(spbu_id, model_id, serial_number, machine_identifier) values(1, 'Encore S-300.1', '10002', 'Mesin2');
insert into spbu_machines(spbu_id, model_id, serial_number, machine_identifier) values(1, 'Encore S-300.2', '10003', 'Mesin3');
insert into spbu_machines(spbu_id, model_id, serial_number, machine_identifier) values(1, 'Encore S-300.2', '10004', 'Mesin4');

insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.1', 'Assymeter', 'Assymeter1', 'Totalizer1');
insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.1', 'Assymeter', 'Assymeter2', 'Totalizer2');
insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.1', 'Assymeter', 'Assymeter3', 'Totalizer3');
insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.1', 'Assymeter', 'Assymeter4', 'Totalizer4');
insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.1', 'Assymeter', 'Assymeter5', 'Totalizer5');
insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.1', 'Assymeter', 'Assymeter6', 'Totalizer6');

insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.2', 'Assymeter', 'Assymeter1', 'Totalizer1');
insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.2', 'Assymeter', 'Assymeter2', 'Totalizer2');
insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.2', 'Assymeter', 'Assymeter3', 'Totalizer3');
insert into machine_model_part_totalizers(model_id, part_id, machine_model_part_identifier, totalizer_id) values('Encore S-300.2', 'Assymeter', 'Assymeter4', 'Totalizer4');

insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10001', 'Totalizer1', 'Pertamax 1.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10001', 'Totalizer2', 'Pertamax 1.2', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10001', 'Totalizer3', 'Premium 1.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10001', 'Totalizer4', 'Premium 1.2', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10001', 'Totalizer5', 'Solar 1.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10001', 'Totalizer6', 'Solar 1.2', 0);

insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10002', 'Totalizer1', 'Pertamax 2.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10002', 'Totalizer2', 'Pertamax 2.2', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10002', 'Totalizer3', 'Premium 2.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10002', 'Totalizer4', 'Premium 2.2', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10002', 'Totalizer5', 'Solar 2.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.1', '10002', 'Totalizer6', 'Solar 2.2', 0);

insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.2', '10003', 'Totalizer1', 'Pertamax 3.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.2', '10003', 'Totalizer2', 'Pertamax 3.2', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.2', '10003', 'Totalizer3', 'Premium 3.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.2', '10003', 'Totalizer4', 'Premium 3.2', 0);

insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.2', '10003', 'Totalizer1', 'Pertamax 4.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.2', '10003', 'Totalizer2', 'Pertamax 4.2', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.2', '10003', 'Totalizer3', 'Premium 4.1', 0);
insert into spbu_machines(spbu_id, model_id, serial_number, totalizer_id, alias, counter) values(1, 'Encore S-300.2', '10003', 'Totalizer4', 'Premium 4.2', 0);

insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10001', 'Assymeter', 'Assymeter1', 0, 0);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10001', 'Assymeter', 'Assymeter2', 0, 0);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10001', 'Assymeter', 'Assymeter3', 3200000, 3108000);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10001', 'Assymeter', 'Assymeter4', 3200000, 3108000);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10001', 'Assymeter', 'Assymeter3', 5400000, 5372000);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10001', 'Assymeter', 'Assymeter4', 5400000, 5372000);

insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10002', 'Assymeter', 'Assymeter1', 0, 0);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10002', 'Assymeter', 'Assymeter2', 0, 0);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10002', 'Assymeter', 'Assymeter3', 3200000, 3108000);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10002', 'Assymeter', 'Assymeter4', 3200000, 3108000);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10002', 'Assymeter', 'Assymeter3', 5400000, 5372000);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.1', '10002', 'Assymeter', 'Assymeter4', 5400000, 5372000);

insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.2', '10003', 'Assymeter', 'Assymeter1', 0, 0);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.2', '10003', 'Assymeter', 'Assymeter2', 0, 0);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.2', '10003', 'Assymeter', 'Assymeter3', 3200000, 3108000);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.2', '10003', 'Assymeter', 'Assymeter4', 3200000, 3108000);

insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.2', '10004', 'Assymeter', 'Assymeter1', 0, 0);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.2', '10004', 'Assymeter', 'Assymeter2', 0, 0);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.2', '10004', 'Assymeter', 'Assymeter3', 3200000, 3108000);
insert into spbu_machine_part_mttfs(spbu_id, model_id, serial_number, part_id, machine_model_part_identifier, mttf, mttf_threshold) values(1, 'Encore S-300.2', '10004', 'Assymeter', 'Assymeter4', 3200000, 3108000);
