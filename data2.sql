ALTER TABLE users AUTO_INCREMENT = 0;
ALTER TABLE spbus AUTO_INCREMENT = 0;

start transaction;
insert into users(full_name, access_role, login_id, password_hash) values('Administrator', 0, 'admin', 'admin');
insert into users(full_name, access_role, login_id, password_hash) values('Ketut Sukamawa', 1, 'sukamawa', '123');
insert into users(full_name, access_role, login_id, password_hash) values('Putu Juliarta', 2, 'juliarta', '123');

insert into spbus(code, address, phone, supervisor_id) values('54-801.21', 'Jalan Imam Bonjol', '0361 701111', 3);

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

insert into part_failure_modes(part_id, failure_mode_code, name, description) values('Assymeter', 'ASSY-001', 'Volume BBM Tidak Stabil', 'Volume BBM yang dikeluarkan mesin tidak stabil');
insert into part_failure_modes(part_id, failure_mode_code, name, description) values('Assymeter', 'ASSY-002', 'Tidak Mengeluarkan BBM', 'Tidak ada BBM yang dikeluarkan meskipun handle nozzle telah ditekan');
insert into part_failure_modes(part_id, failure_mode_code, name, description) values('Filter', 'FLTR-001', 'Aliran BBM Tidak Maksimal', 'Aliran volume BBM yang dikeluarkan tidak dapat mencapai kecepatan maksimal');

insert into failure_mode_handlings(part_id, failure_mode_code, failure_mode_handling_code, name, description) values('Assymeter', 'ASSY-001', 'ASSY-001.H-001', 'Perbaiki Bearing & Liner', 'Perbaiki Bearing & Liner');
insert into failure_mode_handlings(part_id, failure_mode_code, failure_mode_handling_code, name, description) values('Assymeter', 'ASSY-002', 'ASSY-002.H-001', 'Perbaiki Shaft Utama', 'Perbaiki Shaft Utama');
insert into failure_mode_handlings(part_id, failure_mode_code, failure_mode_handling_code, name, description) values('Filter', 'FLTR-001', 'FLTR-001.H-001', 'Dibersihkan', 'Dibersihkan');
insert into failure_mode_handlings(part_id, failure_mode_code, failure_mode_handling_code, name, description) values('Filter', 'FLTR-001', 'FLTR-001.H-002', 'Ganti Baru', 'Ganti Baru');

insert into spbu_machines(machine_serial, spbu_id, model_id, machine_identifier) values('10001', 1, 'Encore S-300.1', 'Mesin1');
insert into spbu_machines(machine_serial, spbu_id, model_id, machine_identifier) values('10002', 1, 'Encore S-300.1', 'Mesin2');
insert into spbu_machines(machine_serial, spbu_id, model_id, machine_identifier) values('10003', 1, 'Encore S-300.2', 'Mesin3');
insert into spbu_machines(machine_serial, spbu_id, model_id, machine_identifier) values('10004', 1, 'Encore S-300.2', 'Mesin4');

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

insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10001', 'Encore S-300.1', 'Totalizer1', 'Pertamax 1.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10001', 'Encore S-300.1', 'Totalizer2', 'Pertamax 1.2', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10001', 'Encore S-300.1', 'Totalizer3', 'Premium 1.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10001', 'Encore S-300.1', 'Totalizer4', 'Premium 1.2', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10001', 'Encore S-300.1', 'Totalizer5', 'Solar 1.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10001', 'Encore S-300.1', 'Totalizer6', 'Solar 1.2', 0);

insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10002', 'Encore S-300.1', 'Totalizer1', 'Pertamax 2.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10002', 'Encore S-300.1', 'Totalizer2', 'Pertamax 2.2', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10002', 'Encore S-300.1', 'Totalizer3', 'Premium 2.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10002', 'Encore S-300.1', 'Totalizer4', 'Premium 2.2', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10002', 'Encore S-300.1', 'Totalizer5', 'Solar 2.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10002', 'Encore S-300.1', 'Totalizer6', 'Solar 2.2', 0);

insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10003', 'Encore S-300.2', 'Totalizer1', 'Pertamax 3.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10003', 'Encore S-300.2', 'Totalizer2', 'Pertamax 3.2', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10003', 'Encore S-300.2', 'Totalizer3', 'Premium 3.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10003', 'Encore S-300.2', 'Totalizer4', 'Premium 3.2', 0);

insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10004', 'Encore S-300.2', 'Totalizer1', 'Pertamax 4.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10004', 'Encore S-300.2', 'Totalizer2', 'Pertamax 4.2', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10004', 'Encore S-300.2', 'Totalizer3', 'Premium 4.1', 0);
insert into spbu_machine_totalizers(machine_serial, model_id, totalizer_id, alias, counter) values('10004', 'Encore S-300.2', 'Totalizer4', 'Premium 4.2', 0);

insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10001', 'Encore S-300.1', 'Assymeter', 'Assymeter1', -1, 0);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10001', 'Encore S-300.1', 'Assymeter', 'Assymeter2', -1, 0);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10001', 'Encore S-300.1', 'Assymeter', 'Assymeter3', 3200000, 3108000);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10001', 'Encore S-300.1', 'Assymeter', 'Assymeter4', 3200000, 3108000);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10001', 'Encore S-300.1', 'Assymeter', 'Assymeter5', 5400000, 5372000);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10001', 'Encore S-300.1', 'Assymeter', 'Assymeter6', 5400000, 5372000);

insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10002', 'Encore S-300.1', 'Assymeter', 'Assymeter1', -1, 0);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10002', 'Encore S-300.1', 'Assymeter', 'Assymeter2', -1, 0);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10002', 'Encore S-300.1', 'Assymeter', 'Assymeter3', 3200000, 3108000);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10002', 'Encore S-300.1', 'Assymeter', 'Assymeter4', 3200000, 3108000);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10002', 'Encore S-300.1', 'Assymeter', 'Assymeter5', 5400000, 5372000);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10002', 'Encore S-300.1', 'Assymeter', 'Assymeter6', 5400000, 5372000);

insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10003', 'Encore S-300.2', 'Assymeter', 'Assymeter1', -1, 0);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10003', 'Encore S-300.2', 'Assymeter', 'Assymeter2', -1, 0);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10003', 'Encore S-300.2', 'Assymeter', 'Assymeter3', 3200000, 3108000);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10003', 'Encore S-300.2', 'Assymeter', 'Assymeter4', 3200000, 3108000);

insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10004', 'Encore S-300.2', 'Assymeter', 'Assymeter1', -1, 0);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10004', 'Encore S-300.2', 'Assymeter', 'Assymeter2', -1, 0);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10004', 'Encore S-300.2', 'Assymeter', 'Assymeter3', 3200000, 3108000);
insert into spbu_machine_part_mttfs(machine_serial, model_id, part_id, machine_model_part_identifier, mttf, mttf_threshold) values('10004', 'Encore S-300.2', 'Assymeter', 'Assymeter4', 3200000, 3108000);
commit;
