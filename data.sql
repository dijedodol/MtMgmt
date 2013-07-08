ALTER TABLE users AUTO_INCREMENT = 0;
ALTER TABLE spbus AUTO_INCREMENT = 0;
ALTER TABLE machine_models AUTO_INCREMENT = 0;
ALTER TABLE machine_totalizers AUTO_INCREMENT = 0;
ALTER TABLE machine_parts AUTO_INCREMENT = 0;
ALTER TABLE part_failure_modes AUTO_INCREMENT = 0;
ALTER TABLE failure_mode_handlings AUTO_INCREMENT = 0;

insert into users(access_role, full_name, login_id, password_hash) values(0, 'Administrator', 'adm', 'adm');
insert into users(access_role, full_name, login_id, password_hash) values(1, 'Technician', 'tch', 'tch');
insert into users(access_role, full_name, login_id, password_hash) values(2, 'Supervisor', 'spv', 'spv');
insert into users(access_role, full_name, login_id, password_hash) values(2, 'Supervisor Palsu', 'spvpalsu', 'spvpalsu');

insert into spbus(code, address, phone, supervisor_id) values('54-801.21', 'Jalan Imam Bonjol', '0361 701111', 3);
insert into spbus(code, address, phone, supervisor_id) values('Palsu', 'Jalan Palsu', '0361 Palsu', 4);

insert into machine_models(code, name) values('Encore S-300.1', 'Encore S-300 6 Assy');
insert into machine_models(code, name) values('Encore S-300.2', 'Encore S-300 4 Assy');

insert into machine_totalizers(name) values('Totalizer_1');
insert into machine_totalizers(name) values('Totalizer_2');
insert into machine_totalizers(name) values('Totalizer_3');
insert into machine_totalizers(name) values('Totalizer_4');
insert into machine_totalizers(name) values('Totalizer_5');
insert into machine_totalizers(name) values('Totalizer_6');
insert into machine_totalizers(name) values('Totalizer_1');
insert into machine_totalizers(name) values('Totalizer_2');
insert into machine_totalizers(name) values('Totalizer_3');
insert into machine_totalizers(name) values('Totalizer_4');

insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(1, 1);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(1, 2);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(1, 3);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(1, 4);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(1, 5);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(1, 6);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(2, 7);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(2, 8);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(2, 9);
insert into machine_model_totalizers(machine_model_id, machine_totalizer_id) values(2, 10);

insert into machine_parts(code, name) values('ASSYMETER', 'Assymeter');
insert into machine_parts(code, name) values('FILTER', 'Filter');
insert into machine_parts(code, name) values('SOLENOID VALVE', 'Solenoid Valve');
insert into machine_parts(code, name) values('NOZZLE', 'Nozzle');

insert into machine_model_parts(machine_model_id, machine_part_id, mttf, mttf_threshold) values(1, 1, 0, 0);
insert into machine_model_parts(machine_model_id, machine_part_id, mttf, mttf_threshold) values(1, 2, 30, 23);
insert into machine_model_parts(machine_model_id, machine_part_id, mttf, mttf_threshold) values(1, 3, 90, 83);
insert into machine_model_parts(machine_model_id, machine_part_id, mttf, mttf_threshold) values(1, 4, 180, 173);
insert into machine_model_parts(machine_model_id, machine_part_id, mttf, mttf_threshold) values(2, 1, 0, 0);
insert into machine_model_parts(machine_model_id, machine_part_id, mttf, mttf_threshold) values(2, 2, 30, 23);
insert into machine_model_parts(machine_model_id, machine_part_id, mttf, mttf_threshold) values(2, 3, 90, 83);
insert into machine_model_parts(machine_model_id, machine_part_id, mttf, mttf_threshold) values(2, 4, 180, 173);

insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(1, 1, 1);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(1, 1, 2);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(1, 1, 3);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(1, 1, 4);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(1, 1, 5);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(1, 1, 6);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(2, 1, 7);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(2, 1, 8);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(2, 1, 9);
insert into machine_model_part_totalizers(machine_model_id, machine_part_id, machine_totalizer_id) values(2, 1, 10);

insert into part_failure_modes(name, part_id) values('Volume Minyak Tidak Stabil', 1);
insert into part_failure_modes(name, part_id) values('Minyak Tidak Keluar', 1);
insert into part_failure_modes(name, part_id) values('Aliran Minyak Tidak Maksimal', 2);

insert into failure_mode_handlings(name, failure_mode_id) values('Perbaiki Bearing & Liner', 1);
insert into failure_mode_handlings(name, failure_mode_id) values('Perbaiki Shaft Utama', 2);
insert into failure_mode_handlings(name, failure_mode_id) values('Dibersihkan', 3);
insert into failure_mode_handlings(name, failure_mode_id) values('Ganti Baru', 3);

insert into spbu_machines(spbu_id, machine_identifier, machine_model_id) values(1, 'Mesin1', 1);
insert into spbu_machines(spbu_id, machine_identifier, machine_model_id) values(1, 'Mesin2', 1);
insert into spbu_machines(spbu_id, machine_identifier, machine_model_id) values(1, 'Mesin3', 2);
insert into spbu_machines(spbu_id, machine_identifier, machine_model_id) values(1, 'Mesin4', 2);

insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin1', 1, 'Premium1', 0, 3200000, 3108000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin1', 2, 'Premium2', 0, 3200000, 3108000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin1', 3, 'Pertamax1', 0, 0, 0);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin1', 4, 'Pertamax2', 0, 0, 0);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin1', 5, 'Solar1', 0, 5400000, 5372000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin1', 6, 'Solar2', 0, 5400000, 5372000);

insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin2', 1, 'Premium3', 0, 3200000, 3108000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin2', 2, 'Premium4', 0, 3200000, 3108000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin2', 3, 'Pertamax3', 0, 0, 0);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin2', 4, 'Pertamax4', 0, 0, 0);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin2', 5, 'Solar3', 0, 5400000, 5372000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin2', 6, 'Solar4', 0, 5400000, 5372000);

insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin3', 7, 'Premium3', 0, 3200000, 3108000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin3', 8, 'Premium4', 0, 3200000, 3108000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin3', 9, 'Pertamax3', 0, 0, 0);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin3', 10, 'Pertamax4', 0, 0, 0);

insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin4', 7, 'Premium3', 0, 3200000, 3108000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin4', 8, 'Premium4', 0, 3200000, 3108000);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin4', 9, 'Pertamax3', 0, 0, 0);
insert into spbu_machine_totalizers(spbu_id, machine_identifier, machine_totalizer_id, alias, counter, mttf, mttf_threshold) values(1, 'Mesin4', 10, 'Pertamax4', 0, 0, 0);
