use healthsure;
-- Insert 2 doctors
INSERT INTO Providers (provider_id, provider_name, hospital_name, email, address, city, state, zip_code, status)
VALUES
('PROV001', 'Dr. Mehta', 'Sunrise Hospital', 'mehta@sunrisehosp.com', '123 MG Road', 'Mumbai', 'Maharashtra', '400001', 'APPROVED');
INSERT INTO Doctors (doctor_id, provider_id, doctor_name, qualification, specialization, license_no, email, address, gender, password, login_status, doctor_status)
VALUES 
('DOC001', 'PROV001', 'Dr. Smith Johnson', 'MD, Cardiology', 'Cardiologist', 'LIC001', 'dr.smith@hospital.com', '123 Medical St, City', 'MALE', 'hashed_password1', 'APPROVED', 'ACTIVE'),
('DOC002','PROV001', 'Dr.  Johnson', 'MD, Cardiology', 'Cardiologist', 'LIC002', 'dr.smith@hosp.com', '123 Medical St, City', 'MALE', 'hashed_password1', 'APPROVED', 'ACTIVE');
-- Insert 10 patients
INSERT INTO Recipient (h_id, first_name, last_name, mobile, user_name, gender, dob, address, password, email, status)
VALUES
('H001', 'John', 'Doe', '1234567890', 'johndoe', 'MALE', '1980-05-15', '123 Main St, City', 'hashed_pass1', 'john@email.com', 'ACTIVE'),
('H002', 'Jane', 'Smith', '2345678901', 'janesmith', 'FEMALE', '1985-08-20', '456 Oak Ave, Town', 'hashed_pass2', 'jane@email.com', 'ACTIVE'),
('H003', 'Robert', 'Johnson', '3456789012', 'robertj', 'MALE', '1975-03-10', '789 Pine Rd, Village', 'hashed_pass3', 'robert@email.com', 'ACTIVE'),
('H004', 'Sarah', 'Williams', '4567890123', 'sarahw', 'FEMALE', '1990-11-25', '321 Elm St, City', 'hashed_pass4', 'sarah@email.com', 'ACTIVE'),
('H005', 'Michael', 'Brown', '5678901234', 'michaelb', 'MALE', '1988-07-30', '654 Maple Dr, Town', 'hashed_pass5', 'michael@email.com', 'ACTIVE'),
('H006', 'Emily', 'Davis', '6789012345', 'emilyd', 'FEMALE', '1995-02-14', '987 Cedar Ln, Village', 'hashed_pass6', 'emily@email.com', 'ACTIVE'),
('H007', 'David', 'Miller', '7890123456', 'davidm', 'MALE', '1982-09-05', '159 Birch Blvd, City', 'hashed_pass7', 'david@email.com', 'ACTIVE'),
('H008', 'Jessica', 'Wilson', '8901234567', 'jessicaw', 'FEMALE', '1978-12-18', '753 Spruce Way, Town', 'hashed_pass8', 'jessica@email.com', 'ACTIVE'),
('H009', 'Daniel', 'Taylor', '9012345678', 'danielt', 'MALE', '1992-06-22', '246 Willow Ct, Village', 'hashed_pass9', 'daniel@email.com', 'ACTIVE'),
('H010', 'Amanda', 'Anderson', '0123456789', 'amandaa', 'FEMALE', '1987-04-08', '369 Aspen Pl, City', 'hashed_pass10', 'amanda@email.com', 'ACTIVE');
-- Insert insurance companies
INSERT INTO Insurance_company (company_id, name, logo_url, head_office, contact_email, contact_phone)
VALUES
('COMP001', 'HealthGuard', 'logo1.png', 'New York', 'contact@healthguard.com', '18001234567'),
('COMP002', 'LifeSecure', 'logo2.png', 'Chicago', 'info@lifesecure.com', '18009876543'),
('COMP003', 'WellCare', 'logo3.png', 'Los Angeles', 'support@wellcare.com', '18005556677');

-- Insert insurance plans (20 plans)
INSERT INTO Insurance_plan (plan_id, company_id, plan_name, plan_type, min_entry_age, max_entry_age, description, available_cover_amounts, waiting_period, periodic_diseases)
VALUES
('PLAN001', 'COMP001', 'HealthGuard Gold', 'SELF', 18, 65, 'Comprehensive individual coverage', '500000,1000000,2000000', '30 days', 'NO'),
('PLAN002', 'COMP001', 'HealthGuard Family', 'FAMILY', 18, 65, 'Family coverage for up to 10 members', '1000000,2000000,3000000', '30 days', 'NO'),
('PLAN003', 'COMP001', 'HealthGuard Senior', 'SENIOR', 50, 75, 'Special coverage for seniors', '300000,500000,1000000', '90 days', 'YES'),
('PLAN004', 'COMP002', 'LifeSecure Basic', 'SELF', 18, 60, 'Basic individual coverage', '300000,500000', '60 days', 'NO'),
('PLAN005', 'COMP002', 'LifeSecure Family Plus', 'FAMILY', 18, 60, 'Premium family coverage', '1500000,2500000', '60 days', 'NO'),
('PLAN006', 'COMP002', 'LifeSecure Critical', 'CRITICAL_ILLNESS', 25, 65, 'Critical illness coverage', '1000000,2000000', '180 days', 'YES'),
('PLAN007', 'COMP003', 'WellCare Standard', 'SELF', 20, 70, 'Standard individual plan', '400000,800000', '45 days', 'NO'),
('PLAN008', 'COMP003', 'WellCare Family', 'FAMILY', 20, 70, 'Standard family plan', '1200000,1800000', '45 days', 'NO'),
('PLAN009', 'COMP003', 'WellCare Senior Plus', 'SENIOR', 55, 80, 'Senior coverage with extra benefits', '500000,1000000', '90 days', 'YES'),
('PLAN010', 'COMP001', 'HealthGuard Platinum', 'SELF', 21, 65, 'Premium individual coverage', '1000000,2000000,3000000', '15 days', 'NO'),
('PLAN011', 'COMP001', 'HealthGuard Executive', 'FAMILY', 21, 65, 'Executive family coverage', '2000000,3000000,5000000', '15 days', 'NO'),
('PLAN012', 'COMP002', 'LifeSecure Gold', 'SELF', 18, 65, 'Gold level individual coverage', '750000,1500000', '30 days', 'NO'),
('PLAN013', 'COMP002', 'LifeSecure Silver', 'SELF', 18, 65, 'Silver level individual coverage', '500000,1000000', '30 days', 'NO'),
('PLAN014', 'COMP002', 'LifeSecure Bronze', 'SELF', 18, 65, 'Bronze level individual coverage', '250000,500000', '30 days', 'NO'),
('PLAN015', 'COMP003', 'WellCare Premium', 'SELF', 18, 70, 'Premium individual coverage', '1000000,2000000', '30 days', 'NO'),
('PLAN016', 'COMP003', 'WellCare Essential', 'SELF', 18, 70, 'Essential individual coverage', '300000,600000', '30 days', 'NO'),
('PLAN017', 'COMP003', 'WellCare Critical Plus', 'CRITICAL_ILLNESS', 30, 65, 'Enhanced critical illness coverage', '1500000,3000000', '120 days', 'YES'),
('PLAN018', 'COMP001', 'HealthGuard Critical', 'CRITICAL_ILLNESS', 25, 65, 'Critical illness coverage', '1000000,2000000', '120 days', 'YES'),
('PLAN019', 'COMP002', 'LifeSecure Senior', 'SENIOR', 50, 75, 'Senior coverage plan', '400000,800000', '90 days', 'YES'),
('PLAN020', 'COMP003', 'WellCare Family Plus', 'FAMILY', 20, 70, 'Enhanced family coverage', '1500000,2500000', '30 days', 'NO');
-- Insert coverage options (multiple per plan)
INSERT INTO Insurance_coverage_option (coverage_id, plan_id, premium_amount, coverage_amount)
VALUES
('COV001', 'PLAN001', 5000, 500000),
('COV002', 'PLAN001', 8000, 1000000),
('COV003', 'PLAN001', 12000, 2000000),
('COV004', 'PLAN002', 15000, 1000000),
('COV005', 'PLAN002', 22000, 2000000),
('COV006', 'PLAN002', 30000, 3000000),
('COV007', 'PLAN003', 10000, 300000),
('COV008', 'PLAN003', 15000, 500000),
('COV009', 'PLAN003', 25000, 1000000),
('COV010', 'PLAN004', 3000, 300000),
('COV011', 'PLAN004', 4500, 500000),
('COV012', 'PLAN005', 18000, 1500000),
('COV013', 'PLAN005', 28000, 2500000),
('COV014', 'PLAN006', 12000, 1000000),
('COV015', 'PLAN006', 20000, 2000000),
('COV016', 'PLAN007', 4000, 400000),
('COV017', 'PLAN007', 7000, 800000),
('COV018', 'PLAN008', 12000, 1200000),
('COV019', 'PLAN008', 16000, 1800000),
('COV020', 'PLAN009', 12000, 500000),
('COV021', 'PLAN009', 18000, 1000000),
('COV022', 'PLAN010', 10000, 1000000),
('COV023', 'PLAN010', 18000, 2000000),
('COV024', 'PLAN010', 25000, 3000000),
('COV025', 'PLAN011', 25000, 2000000),
('COV026', 'PLAN011', 35000, 3000000),
('COV027', 'PLAN011', 50000, 5000000),
('COV028', 'PLAN012', 7000, 750000),
('COV029', 'PLAN012', 12000, 1500000),
('COV030', 'PLAN013', 5000, 500000),
('COV031', 'PLAN013', 8000, 1000000),
('COV032', 'PLAN014', 3000, 250000),
('COV033', 'PLAN014', 4500, 500000),
('COV034', 'PLAN015', 10000, 1000000),
('COV035', 'PLAN015', 18000, 2000000),
('COV036', 'PLAN016', 3000, 300000),
('COV037', 'PLAN016', 5000, 600000),
('COV038', 'PLAN017', 15000, 1500000),
('COV039', 'PLAN017', 25000, 3000000),
('COV040', 'PLAN018', 12000, 1000000),
('COV041', 'PLAN018', 20000, 2000000),
('COV042', 'PLAN019', 10000, 400000),
('COV043', 'PLAN019', 15000, 800000),
('COV044', 'PLAN020', 20000, 1500000),
('COV045', 'PLAN020', 30000, 2500000);
-- Insert subscriptions for patients (5 patients with 20 plans each)
-- For H001 (20 plans - mix of individual and family)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB001', 'H001', 'COV001', '2023-01-01', '2024-01-01', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB002', 'H001', 'COV004', '2023-02-01', '2024-02-01', 'FAMILY', 'ACTIVE', 15000, 15000),
('SUB003', 'H001', 'COV007', '2023-03-01', '2024-03-01', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB004', 'H001', 'COV010', '2023-04-01', '2024-04-01', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB005', 'H001', 'COV012', '2023-05-01', '2024-05-01', 'FAMILY', 'ACTIVE', 18000, 18000),
('SUB006', 'H001', 'COV014', '2023-06-01', '2024-06-01', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB007', 'H001', 'COV016', '2023-07-01', '2024-07-01', 'INDIVIDUAL', 'ACTIVE', 4000, 4000),
('SUB008', 'H001', 'COV018', '2023-08-01', '2024-08-01', 'FAMILY', 'ACTIVE', 12000, 12000),
('SUB009', 'H001', 'COV020', '2023-09-01', '2024-09-01', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB010', 'H001', 'COV022', '2023-10-01', '2024-10-01', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB011', 'H001', 'COV025', '2023-11-01', '2024-11-01', 'FAMILY', 'ACTIVE', 25000, 25000),
('SUB012', 'H001', 'COV028', '2023-12-01', '2024-12-01', 'INDIVIDUAL', 'ACTIVE', 7000, 7000),
('SUB013', 'H001', 'COV030', '2024-01-01', '2025-01-01', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB014', 'H001', 'COV032', '2024-02-01', '2025-02-01', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB015', 'H001', 'COV034', '2024-03-01', '2025-03-01', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB016', 'H001', 'COV036', '2024-04-01', '2025-04-01', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB017', 'H001', 'COV038', '2024-05-01', '2025-05-01', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB018', 'H001', 'COV040', '2024-06-01', '2025-06-01', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB019', 'H001', 'COV042', '2024-07-01', '2025-07-01', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB020', 'H001', 'COV044', '2024-08-01', '2025-08-01', 'FAMILY', 'ACTIVE', 20000, 20000);

-- For H002 (20 plans)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB021', 'H002', 'COV002', '2023-01-15', '2024-01-15', 'INDIVIDUAL', 'ACTIVE', 8000, 8000),
('SUB022', 'H002', 'COV005', '2023-02-15', '2024-02-15', 'FAMILY', 'ACTIVE', 22000, 22000),
('SUB023', 'H002', 'COV008', '2023-03-15', '2024-03-15', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB024', 'H002', 'COV011', '2023-04-15', '2024-04-15', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB025', 'H002', 'COV013', '2023-05-15', '2024-05-15', 'FAMILY', 'ACTIVE', 28000, 28000),
('SUB026', 'H002', 'COV015', '2023-06-15', '2024-06-15', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB027', 'H002', 'COV017', '2023-07-15', '2024-07-15', 'INDIVIDUAL', 'ACTIVE', 7000, 7000),
('SUB028', 'H002', 'COV019', '2023-08-15', '2024-08-15', 'FAMILY', 'ACTIVE', 16000, 16000),
('SUB029', 'H002', 'COV021', '2023-09-15', '2024-09-15', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB030', 'H002', 'COV023', '2023-10-15', '2024-10-15', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB031', 'H002', 'COV026', '2023-11-15', '2024-11-15', 'FAMILY', 'ACTIVE', 35000, 35000),
('SUB032', 'H002', 'COV029', '2023-12-15', '2024-12-15', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB033', 'H002', 'COV031', '2024-01-15', '2025-01-15', 'INDIVIDUAL', 'ACTIVE', 8000, 8000),
('SUB034', 'H002', 'COV033', '2024-02-15', '2025-02-15', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB035', 'H002', 'COV035', '2024-03-15', '2025-03-15', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB036', 'H002', 'COV037', '2024-04-15', '2025-04-15', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB037', 'H002', 'COV039', '2024-05-15', '2025-05-15', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB038', 'H002', 'COV041', '2024-06-15', '2025-06-15', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB039', 'H002', 'COV043', '2024-07-15', '2025-07-15', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB040', 'H002', 'COV045', '2024-08-15', '2025-08-15', 'FAMILY', 'ACTIVE', 30000, 30000);
-- For H003 (20 plans - mix of individual and family)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB041', 'H003', 'COV003', '2023-01-05', '2024-01-05', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB042', 'H003', 'COV006', '2023-02-05', '2024-02-05', 'FAMILY', 'ACTIVE', 30000, 30000),
('SUB043', 'H003', 'COV009', '2023-03-05', '2024-03-05', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB044', 'H003', 'COV011', '2023-04-05', '2024-04-05', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB045', 'H003', 'COV013', '2023-05-05', '2024-05-05', 'FAMILY', 'ACTIVE', 28000, 28000),
('SUB046', 'H003', 'COV015', '2023-06-05', '2024-06-05', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB047', 'H003', 'COV017', '2023-07-05', '2024-07-05', 'INDIVIDUAL', 'ACTIVE', 7000, 7000),
('SUB048', 'H003', 'COV019', '2023-08-05', '2024-08-05', 'FAMILY', 'ACTIVE', 16000, 16000),
('SUB049', 'H003', 'COV021', '2023-09-05', '2024-09-05', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB050', 'H003', 'COV023', '2023-10-05', '2024-10-05', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB051', 'H003', 'COV026', '2023-11-05', '2024-11-05', 'FAMILY', 'ACTIVE', 35000, 35000),
('SUB052', 'H003', 'COV029', '2023-12-05', '2024-12-05', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB053', 'H003', 'COV031', '2024-01-05', '2025-01-05', 'INDIVIDUAL', 'ACTIVE', 8000, 8000),
('SUB054', 'H003', 'COV033', '2024-02-05', '2025-02-05', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB055', 'H003', 'COV035', '2024-03-05', '2025-03-05', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB056', 'H003', 'COV037', '2024-04-05', '2025-04-05', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB057', 'H003', 'COV039', '2024-05-05', '2025-05-05', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB058', 'H003', 'COV041', '2024-06-05', '2025-06-05', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB059', 'H003', 'COV043', '2024-07-05', '2025-07-05', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB060', 'H003', 'COV045', '2024-08-05', '2025-08-05', 'FAMILY', 'ACTIVE', 30000, 30000);

-- For H004 (20 plans - mix of individual and family)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB061', 'H004', 'COV001', '2023-01-10', '2024-01-10', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB062', 'H004', 'COV004', '2023-02-10', '2024-02-10', 'FAMILY', 'ACTIVE', 15000, 15000),
('SUB063', 'H004', 'COV007', '2023-03-10', '2024-03-10', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB064', 'H004', 'COV010', '2023-04-10', '2024-04-10', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB065', 'H004', 'COV012', '2023-05-10', '2024-05-10', 'FAMILY', 'ACTIVE', 18000, 18000),
('SUB066', 'H004', 'COV014', '2023-06-10', '2024-06-10', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB067', 'H004', 'COV016', '2023-07-10', '2024-07-10', 'INDIVIDUAL', 'ACTIVE', 4000, 4000),
('SUB068', 'H004', 'COV018', '2023-08-10', '2024-08-10', 'FAMILY', 'ACTIVE', 12000, 12000),
('SUB069', 'H004', 'COV020', '2023-09-10', '2024-09-10', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB070', 'H004', 'COV022', '2023-10-10', '2024-10-10', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB071', 'H004', 'COV025', '2023-11-10', '2024-11-10', 'FAMILY', 'ACTIVE', 25000, 25000),
('SUB072', 'H004', 'COV028', '2023-12-10', '2024-12-10', 'INDIVIDUAL', 'ACTIVE', 7000, 7000),
('SUB073', 'H004', 'COV030', '2024-01-10', '2025-01-10', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB074', 'H004', 'COV032', '2024-02-10', '2025-02-10', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB075', 'H004', 'COV034', '2024-03-10', '2025-03-10', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB076', 'H004', 'COV036', '2024-04-10', '2025-04-10', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB077', 'H004', 'COV038', '2024-05-10', '2025-05-10', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB078', 'H004', 'COV040', '2024-06-10', '2025-06-10', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB079', 'H004', 'COV042', '2024-07-10', '2025-07-10', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB080', 'H004', 'COV044', '2024-08-10', '2025-08-10', 'FAMILY', 'ACTIVE', 20000, 20000);

-- For H005 (20 plans - mix of individual and family)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB081', 'H005', 'COV002', '2023-01-15', '2024-01-15', 'INDIVIDUAL', 'ACTIVE', 8000, 8000),
('SUB082', 'H005', 'COV005', '2023-02-15', '2024-02-15', 'FAMILY', 'ACTIVE', 22000, 22000),
('SUB083', 'H005', 'COV008', '2023-03-15', '2024-03-15', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB084', 'H005', 'COV011', '2023-04-15', '2024-04-15', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB085', 'H005', 'COV013', '2023-05-15', '2024-05-15', 'FAMILY', 'ACTIVE', 28000, 28000),
('SUB086', 'H005', 'COV015', '2023-06-15', '2024-06-15', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB087', 'H005', 'COV017', '2023-07-15', '2024-07-15', 'INDIVIDUAL', 'ACTIVE', 7000, 7000),
('SUB088', 'H005', 'COV019', '2023-08-15', '2024-08-15', 'FAMILY', 'ACTIVE', 16000, 16000),
('SUB089', 'H005', 'COV021', '2023-09-15', '2024-09-15', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB090', 'H005', 'COV023', '2023-10-15', '2024-10-15', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB091', 'H005', 'COV026', '2023-11-15', '2024-11-15', 'FAMILY', 'ACTIVE', 35000, 35000),
('SUB092', 'H005', 'COV029', '2023-12-15', '2024-12-15', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB093', 'H005', 'COV031', '2024-01-15', '2025-01-15', 'INDIVIDUAL', 'ACTIVE', 8000, 8000),
('SUB094', 'H005', 'COV033', '2024-02-15', '2025-02-15', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB095', 'H005', 'COV035', '2024-03-15', '2025-03-15', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB096', 'H005', 'COV037', '2024-04-15', '2025-04-15', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB097', 'H005', 'COV039', '2024-05-15', '2025-05-15', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB098', 'H005', 'COV041', '2024-06-15', '2025-06-15', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB099', 'H005', 'COV043', '2024-07-15', '2025-07-15', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB100', 'H005', 'COV045', '2024-08-15', '2025-08-15', 'FAMILY', 'ACTIVE', 30000, 30000);
-- For H006 (15 plans - fewer than the first 5 patients)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB101', 'H006', 'COV003', '2023-01-20', '2024-01-20', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB102', 'H006', 'COV006', '2023-02-20', '2024-02-20', 'FAMILY', 'ACTIVE', 30000, 30000),
('SUB103', 'H006', 'COV009', '2023-03-20', '2024-03-20', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB104', 'H006', 'COV012', '2023-04-20', '2024-04-20', 'FAMILY', 'ACTIVE', 18000, 18000),
('SUB105', 'H006', 'COV015', '2023-05-20', '2024-05-20', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB106', 'H006', 'COV018', '2023-06-20', '2024-06-20', 'FAMILY', 'ACTIVE', 12000, 12000),
('SUB107', 'H006', 'COV021', '2023-07-20', '2024-07-20', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB108', 'H006', 'COV024', '2023-08-20', '2024-08-20', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB109', 'H006', 'COV027', '2023-09-20', '2024-09-20', 'FAMILY', 'ACTIVE', 50000, 50000),
('SUB110', 'H006', 'COV030', '2023-10-20', '2024-10-20', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB111', 'H006', 'COV033', '2023-11-20', '2024-11-20', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB112', 'H006', 'COV036', '2023-12-20', '2024-12-20', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB113', 'H006', 'COV039', '2024-01-20', '2025-01-20', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB114', 'H006', 'COV042', '2024-02-20', '2025-02-20', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB115', 'H006', 'COV045', '2024-03-20', '2025-03-20', 'FAMILY', 'ACTIVE', 30000, 30000);

-- For H007 (15 plans)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB116', 'H007', 'COV002', '2023-01-25', '2024-01-25', 'INDIVIDUAL', 'ACTIVE', 8000, 8000),
('SUB117', 'H007', 'COV005', '2023-02-25', '2024-02-25', 'FAMILY', 'ACTIVE', 22000, 22000),
('SUB118', 'H007', 'COV008', '2023-03-25', '2024-03-25', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB119', 'H007', 'COV011', '2023-04-25', '2024-04-25', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB120', 'H007', 'COV014', '2023-05-25', '2024-05-25', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB121', 'H007', 'COV017', '2023-06-25', '2024-06-25', 'INDIVIDUAL', 'ACTIVE', 7000, 7000),
('SUB122', 'H007', 'COV020', '2023-07-25', '2024-07-25', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB123', 'H007', 'COV023', '2023-08-25', '2024-08-25', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB124', 'H007', 'COV026', '2023-09-25', '2024-09-25', 'FAMILY', 'ACTIVE', 35000, 35000),
('SUB125', 'H007', 'COV029', '2023-10-25', '2024-10-25', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB126', 'H007', 'COV032', '2023-11-25', '2024-11-25', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB127', 'H007', 'COV035', '2023-12-25', '2024-12-25', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB128', 'H007', 'COV038', '2024-01-25', '2025-01-25', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB129', 'H007', 'COV041', '2024-02-25', '2025-02-25', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB130', 'H007', 'COV044', '2024-03-25', '2025-03-25', 'FAMILY', 'ACTIVE', 20000, 20000);

-- For H008 (15 plans)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB131', 'H008', 'COV001', '2023-01-30', '2024-01-30', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB132', 'H008', 'COV004', '2023-02-28', '2024-02-28', 'FAMILY', 'ACTIVE', 15000, 15000),
('SUB133', 'H008', 'COV007', '2023-03-30', '2024-03-30', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB134', 'H008', 'COV010', '2023-04-30', '2024-04-30', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB135', 'H008', 'COV013', '2023-05-30', '2024-05-30', 'FAMILY', 'ACTIVE', 28000, 28000),
('SUB136', 'H008', 'COV016', '2023-06-30', '2024-06-30', 'INDIVIDUAL', 'ACTIVE', 4000, 4000),
('SUB137', 'H008', 'COV019', '2023-07-30', '2024-07-30', 'FAMILY', 'ACTIVE', 16000, 16000),
('SUB138', 'H008', 'COV022', '2023-08-30', '2024-08-30', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB139', 'H008', 'COV025', '2023-09-30', '2024-09-30', 'FAMILY', 'ACTIVE', 25000, 25000),
('SUB140', 'H008', 'COV028', '2023-10-30', '2024-10-30', 'INDIVIDUAL', 'ACTIVE', 7000, 7000),
('SUB141', 'H008', 'COV031', '2023-11-30', '2024-11-30', 'INDIVIDUAL', 'ACTIVE', 8000, 8000),
('SUB142', 'H008', 'COV034', '2023-12-30', '2024-12-30', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB143', 'H008', 'COV037', '2024-01-30', '2025-01-30', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB144', 'H008', 'COV040', '2024-02-28', '2025-02-28', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB145', 'H008', 'COV043', '2024-03-30', '2025-03-30', 'INDIVIDUAL', 'ACTIVE', 15000, 15000);

-- For H009 (15 plans)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB146', 'H009', 'COV003', '2023-02-01', '2024-02-01', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB147', 'H009', 'COV006', '2023-03-01', '2024-03-01', 'FAMILY', 'ACTIVE', 30000, 30000),
('SUB148', 'H009', 'COV009', '2023-04-01', '2024-04-01', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB149', 'H009', 'COV012', '2023-05-01', '2024-05-01', 'FAMILY', 'ACTIVE', 18000, 18000),
('SUB150', 'H009', 'COV015', '2023-06-01', '2024-06-01', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB151', 'H009', 'COV018', '2023-07-01', '2024-07-01', 'FAMILY', 'ACTIVE', 12000, 12000),
('SUB152', 'H009', 'COV021', '2023-08-01', '2024-08-01', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB153', 'H009', 'COV024', '2023-09-01', '2024-09-01', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB154', 'H009', 'COV027', '2023-10-01', '2024-10-01', 'FAMILY', 'ACTIVE', 50000, 50000),
('SUB155', 'H009', 'COV030', '2023-11-01', '2024-11-01', 'INDIVIDUAL', 'ACTIVE', 5000, 5000),
('SUB156', 'H009', 'COV033', '2023-12-01', '2024-12-01', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB157', 'H009', 'COV036', '2024-01-01', '2025-01-01', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB158', 'H009', 'COV039', '2024-02-01', '2025-02-01', 'INDIVIDUAL', 'ACTIVE', 25000, 25000),
('SUB159', 'H009', 'COV042', '2024-03-01', '2025-03-01', 'INDIVIDUAL', 'ACTIVE', 10000, 10000),
('SUB160', 'H009', 'COV045', '2024-04-01', '2025-04-01', 'FAMILY', 'ACTIVE', 30000, 30000);

-- For H010 (15 plans)
INSERT INTO subscribe (subscribe_id, h_id, coverage_id, subscribe_date, expiry_date, type, status, total_premium, amount_paid)
VALUES
('SUB161', 'H010', 'COV002', '2023-02-05', '2024-02-05', 'INDIVIDUAL', 'ACTIVE', 8000, 8000),
('SUB162', 'H010', 'COV005', '2023-03-05', '2024-03-05', 'FAMILY', 'ACTIVE', 22000, 22000),
('SUB163', 'H010', 'COV008', '2023-04-05', '2024-04-05', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB164', 'H010', 'COV011', '2023-05-05', '2024-05-05', 'INDIVIDUAL', 'ACTIVE', 4500, 4500),
('SUB165', 'H010', 'COV014', '2023-06-05', '2024-06-05', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB166', 'H010', 'COV017', '2023-07-05', '2024-07-05', 'INDIVIDUAL', 'ACTIVE', 7000, 7000),
('SUB167', 'H010', 'COV020', '2023-08-05', '2024-08-05', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB168', 'H010', 'COV023', '2023-09-05', '2024-09-05', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB169', 'H010', 'COV026', '2023-10-05', '2024-10-05', 'FAMILY', 'ACTIVE', 35000, 35000),
('SUB170', 'H010', 'COV029', '2023-11-05', '2024-11-05', 'INDIVIDUAL', 'ACTIVE', 12000, 12000),
('SUB171', 'H010', 'COV032', '2023-12-05', '2024-12-05', 'INDIVIDUAL', 'ACTIVE', 3000, 3000),
('SUB172', 'H010', 'COV035', '2024-01-05', '2025-01-05', 'INDIVIDUAL', 'ACTIVE', 18000, 18000),
('SUB173', 'H010', 'COV038', '2024-02-05', '2025-02-05', 'INDIVIDUAL', 'ACTIVE', 15000, 15000),
('SUB174', 'H010', 'COV041', '2024-03-05', '2025-03-05', 'INDIVIDUAL', 'ACTIVE', 20000, 20000),
('SUB175', 'H010', 'COV044', '2024-04-05', '2025-04-05', 'FAMILY', 'ACTIVE', 20000, 20000);
-- Similarly for H003, H004, H005 (20 plans each)
-- [Additional INSERT statements for H003, H004, H005 would go here]
-- Insert doctor availability
-- Family members for SUB002 (H001's family plan)
-- SUB002 (H001's family plan)
INSERT INTO subscribed_members VALUES
('MEM001', 'SUB002', 'John Doe', 43, 'MALE', 'SELF', '111122223333'),
('MEM002', 'SUB002', 'Mary Doe', 40, 'FEMALE', 'SPOUSE', '111122223334'),
('MEM003', 'SUB002', 'Tom Doe', 18, 'MALE', 'CHILD', '111122223335'),
('MEM004', 'SUB002', 'Lisa Doe', 16, 'FEMALE', 'CHILD', '111122223336'),
('MEM005', 'SUB002', 'Robert Doe', 12, 'MALE', 'CHILD', '111122223337'),
('MEM006', 'SUB002', 'Susan Doe', 8, 'FEMALE', 'CHILD', '111122223338'),
('MEM007', 'SUB002', 'James Doe', 70, 'MALE', 'PARENT', '111122223339'),
('MEM008', 'SUB002', 'Margaret Doe', 68, 'FEMALE', 'PARENT', '111122223340'),
('MEM009', 'SUB002', 'David Doe', 45, 'MALE', 'SIBLING', '111122223341'),
('MEM010', 'SUB002', 'Sarah Doe', 42, 'FEMALE', 'SIBLING', '111122223342');

-- SUB005 (H001's family plan)
INSERT INTO subscribed_members VALUES
('MEM011', 'SUB005', 'John Doe', 43, 'MALE', 'SELF', '111122223333'),
('MEM012', 'SUB005', 'Mary Doe', 40, 'FEMALE', 'SPOUSE', '111122223334'),
('MEM013', 'SUB005', 'Tom Doe', 18, 'MALE', 'CHILD', '111122223335'),
('MEM014', 'SUB005', 'Lisa Doe', 16, 'FEMALE', 'CHILD', '111122223336'),
('MEM015', 'SUB005', 'Robert Doe', 12, 'MALE', 'CHILD', '111122223337'),
('MEM016', 'SUB005', 'Susan Doe', 8, 'FEMALE', 'CHILD', '111122223338'),
('MEM017', 'SUB005', 'James Doe', 70, 'MALE', 'PARENT', '111122223339'),
('MEM018', 'SUB005', 'Margaret Doe', 68, 'FEMALE', 'PARENT', '111122223340'),
('MEM019', 'SUB005', 'David Doe', 45, 'MALE', 'SIBLING', '111122223341'),
('MEM020', 'SUB005', 'Sarah Doe', 42, 'FEMALE', 'SIBLING', '111122223342');

-- SUB011 (H001's family plan)
INSERT INTO subscribed_members VALUES
('MEM021', 'SUB011', 'John Doe', 43, 'MALE', 'SELF', '111122223333'),
('MEM022', 'SUB011', 'Mary Doe', 40, 'FEMALE', 'SPOUSE', '111122223334'),
('MEM023', 'SUB011', 'Tom Doe', 18, 'MALE', 'CHILD', '111122223335'),
('MEM024', 'SUB011', 'Lisa Doe', 16, 'FEMALE', 'CHILD', '111122223336'),
('MEM025', 'SUB011', 'Robert Doe', 12, 'MALE', 'CHILD', '111122223337'),
('MEM026', 'SUB011', 'Susan Doe', 8, 'FEMALE', 'CHILD', '111122223338'),
('MEM027', 'SUB011', 'James Doe', 70, 'MALE', 'PARENT', '111122223339'),
('MEM028', 'SUB011', 'Margaret Doe', 68, 'FEMALE', 'PARENT', '111122223340'),
('MEM029', 'SUB011', 'David Doe', 45, 'MALE', 'SIBLING', '111122223341'),
('MEM030', 'SUB011', 'Sarah Doe', 42, 'FEMALE', 'SIBLING', '111122223342');

-- SUB020 (H001's family plan)
INSERT INTO subscribed_members VALUES
('MEM031', 'SUB020', 'John Doe', 43, 'MALE', 'SELF', '111122223333'),
('MEM032', 'SUB020', 'Mary Doe', 40, 'FEMALE', 'SPOUSE', '111122223334'),
('MEM033', 'SUB020', 'Tom Doe', 18, 'MALE', 'CHILD', '111122223335'),
('MEM034', 'SUB020', 'Lisa Doe', 16, 'FEMALE', 'CHILD', '111122223336'),
('MEM035', 'SUB020', 'Robert Doe', 12, 'MALE', 'CHILD', '111122223337'),
('MEM036', 'SUB020', 'Susan Doe', 8, 'FEMALE', 'CHILD', '111122223338'),
('MEM037', 'SUB020', 'James Doe', 70, 'MALE', 'PARENT', '111122223339'),
('MEM038', 'SUB020', 'Margaret Doe', 68, 'FEMALE', 'PARENT', '111122223340'),
('MEM039', 'SUB020', 'David Doe', 45, 'MALE', 'SIBLING', '111122223341'),
('MEM040', 'SUB020', 'Sarah Doe', 42, 'FEMALE', 'SIBLING', '111122223342');
INSERT INTO Doctor_availability (availability_id, doctor_id, available_date, start_time, end_time, max_capacity)
VALUES
('AVAIL001', 'DOC001', '2023-10-15', '09:00:00', '17:00:00', 15),
('AVAIL002', 'DOC001', '2023-10-16', '09:00:00', '17:00:00', 15),
('AVAIL003', 'DOC002', '2023-10-15', '10:00:00', '18:00:00', 15),
('AVAIL004', 'DOC002', '2023-10-16', '10:00:00', '18:00:00', 15);

-- Insert appointments for patients with both doctors
-- For DOC001 (Dr. Smith Johnson)
INSERT INTO Appointment (appointment_id, doctor_id, h_id, availability_id, provider_id, booked_at, status)
VALUES
('APP001', 'DOC001', 'H001', 'AVAIL001', 'PROV001', '2023-10-10 10:00:00', 'COMPLETED'),
('APP002', 'DOC001', 'H002', 'AVAIL001', 'PROV001', '2023-10-10 10:30:00', 'COMPLETED'),
('APP003', 'DOC001', 'H003', 'AVAIL001', 'PROV001', '2023-10-10 11:00:00', 'COMPLETED'),
('APP004', 'DOC001', 'H004', 'AVAIL001', 'PROV001', '2023-10-10 11:30:00', 'COMPLETED'),
('APP005', 'DOC001', 'H005', 'AVAIL001', 'PROV001', '2023-10-10 12:00:00', 'COMPLETED'),
('APP006', 'DOC001', 'H006', 'AVAIL001', 'PROV001', '2023-10-10 12:30:00', 'COMPLETED'),
('APP007', 'DOC001', 'H007', 'AVAIL001', 'PROV001', '2023-10-10 13:00:00', 'COMPLETED'),
('APP008', 'DOC001', 'H008', 'AVAIL002', 'PROV001', '2023-10-11 10:00:00', 'COMPLETED'),
('APP009', 'DOC001', 'H009', 'AVAIL002', 'PROV001', '2023-10-11 10:30:00', 'COMPLETED'),
('APP010', 'DOC001', 'H010', 'AVAIL002', 'PROV001', '2023-10-11 11:00:00', 'COMPLETED');

-- For DOC002 (Dr. Emily Davis)
INSERT INTO Appointment (appointment_id, doctor_id, h_id, availability_id, provider_id, booked_at, status)
VALUES
('APP011', 'DOC002', 'H001', 'AVAIL003', 'PROV001', '2023-10-10 10:00:00', 'COMPLETED'),
('APP012', 'DOC002', 'H002', 'AVAIL003', 'PROV001', '2023-10-10 10:30:00', 'COMPLETED'),
('APP013', 'DOC002', 'H003', 'AVAIL003', 'PROV001', '2023-10-10 11:00:00', 'COMPLETED'),
('APP014', 'DOC002', 'H004', 'AVAIL003', 'PROV001', '2023-10-10 11:30:00', 'COMPLETED'),
('APP015', 'DOC002', 'H005', 'AVAIL003', 'PROV001', '2023-10-10 12:00:00', 'COMPLETED'),
('APP016', 'DOC002', 'H006', 'AVAIL003', 'PROV001', '2023-10-10 12:30:00', 'COMPLETED'),
('APP017', 'DOC002', 'H007', 'AVAIL003', 'PROV001', '2023-10-10 13:00:00', 'COMPLETED'),
('APP018', 'DOC002', 'H008', 'AVAIL004', 'PROV001', '2023-10-11 10:00:00', 'COMPLETED'),
('APP019', 'DOC002', 'H009', 'AVAIL004', 'PROV001', '2023-10-11 10:30:00', 'COMPLETED'),
('APP020', 'DOC002', 'H010', 'AVAIL004', 'PROV001', '2023-10-11 11:00:00', 'COMPLETED');

-- Insert medical procedures
INSERT INTO medical_procedure (procedure_id, appointment_id, h_id, provider_id, doctor_id, procedure_date, diagnosis, recommendations)
VALUES
('PROC001', 'APP001', 'H001', 'PROV001', 'DOC001', '2023-10-15', 'Hypertension', 'Medication and lifestyle changes'),
('PROC002', 'APP002', 'H002', 'PROV001', 'DOC001', '2023-10-15', 'High cholesterol', 'Diet modification and statins'),
('PROC003', 'APP003', 'H003', 'PROV001', 'DOC001', '2023-10-15', 'Type 2 Diabetes', 'Insulin therapy and monitoring'),
('PROC004', 'APP004', 'H004', 'PROV001', 'DOC001', '2023-10-15', 'Coronary artery disease', 'Angioplasty recommended'),
('PROC005', 'APP005', 'H005', 'PROV001', 'DOC001', '2023-10-15', 'Arrhythmia', 'Beta blockers prescribed'),
('PROC006', 'APP006', 'H006', 'PROV001', 'DOC001', '2023-10-15', 'Asthma', 'Inhalers and allergy testing'),
('PROC007', 'APP007', 'H007', 'PROV001', 'DOC001', '2023-10-15', 'Heart failure', 'Diuretics and ACE inhibitors'),
('PROC008', 'APP008', 'H008', 'PROV001', 'DOC001', '2023-10-16', 'Angina', 'Nitroglycerin and stress test'),
('PROC009', 'APP009', 'H009', 'PROV001', 'DOC001', '2023-10-16', 'Peripheral artery disease', 'Exercise and medication'),
('PROC010', 'APP010', 'H010', 'PROV001', 'DOC001', '2023-10-16', 'Atrial fibrillation', 'Blood thinners prescribed'),
('PROC011', 'APP011', 'H001', 'PROV001', 'DOC002', '2023-10-15', 'Childhood vaccinations', 'Completed routine immunization'),
('PROC012', 'APP012', 'H002', 'PROV001', 'DOC002', '2023-10-15', 'Pediatric checkup', 'Normal development'),
('PROC013', 'APP013', 'H003', 'PROV001', 'DOC002', '2023-10-15', 'Ear infection', 'Antibiotics prescribed'),
('PROC014', 'APP014', 'H004', 'PROV001', 'DOC002', '2023-10-15', 'Allergy testing', 'Identified pollen allergy'),
('PROC015', 'APP015', 'H005', 'PROV001', 'DOC002', '2023-10-15', 'Well-baby visit', 'Growth on track'),
('PROC016', 'APP016', 'H006', 'PROV001', 'DOC002', '2023-10-15', 'ADHD evaluation', 'Behavioral therapy recommended'),
('PROC017', 'APP017', 'H007', 'PROV001', 'DOC002', '2023-10-15', 'Asthma management', 'Inhaler technique reviewed'),
('PROC018', 'APP018', 'H008', 'PROV001', 'DOC002', '2023-10-16', 'Sports physical', 'Cleared for participation'),
('PROC019', 'APP019', 'H009', 'PROV001', 'DOC002', '2023-10-16', 'Nutrition counseling', 'Diet plan provided'),
('PROC020', 'APP020', 'H010', 'PROV001', 'DOC002', '2023-10-16', 'Developmental screening', 'Normal milestones achieved');
-- Insert prescriptions
INSERT INTO prescription (prescription_id, procedure_id, h_id, provider_id, doctor_id, written_on, start_date, end_date)
VALUES
('PRESC001', 'PROC001', 'H001', 'PROV001', 'DOC001', '2023-10-15 10:30:00', '2023-10-15', '2023-12-15'),
('PRESC002', 'PROC002', 'H002', 'PROV001', 'DOC001', '2023-10-15 11:00:00', '2023-10-15', '2024-01-15'),
('PRESC003', 'PROC003', 'H003', 'PROV001', 'DOC001', '2023-10-15 11:30:00', '2023-10-15', '2023-12-15'),
('PRESC004', 'PROC004', 'H004', 'PROV001', 'DOC001', '2023-10-15 12:00:00', '2023-10-15', '2023-11-15'),
('PRESC005', 'PROC005', 'H005', 'PROV001', 'DOC001', '2023-10-15 12:30:00', '2023-10-15', '2024-01-15'),
('PRESC006', 'PROC006', 'H006', 'PROV001', 'DOC001', '2023-10-15 13:00:00', '2023-10-15', '2023-12-15'),
('PRESC007', 'PROC007', 'H007', 'PROV001', 'DOC001', '2023-10-15 13:30:00', '2023-10-15', '2024-02-15'),
('PRESC008', 'PROC008', 'H008', 'PROV001', 'DOC001', '2023-10-16 10:30:00', '2023-10-16', '2023-12-16'),
('PRESC009', 'PROC009', 'H009', 'PROV001', 'DOC001', '2023-10-16 11:00:00', '2023-10-16', '2024-01-16'),
('PRESC010', 'PROC010', 'H010', 'PROV001', 'DOC001', '2023-10-16 11:30:00', '2023-10-16', '2023-12-16'),
('PRESC011', 'PROC011', 'H001', 'PROV001', 'DOC002', '2023-10-15 10:30:00', '2023-10-15', NULL),
('PRESC012', 'PROC012', 'H002', 'PROV001', 'DOC002', '2023-10-15 11:00:00', '2023-10-15', NULL),
('PRESC013', 'PROC013', 'H003', 'PROV001', 'DOC002', '2023-10-15 11:30:00', '2023-10-15', '2023-10-29'),
('PRESC014', 'PROC014', 'H004', 'PROV001', 'DOC002', '2023-10-15 12:00:00', '2023-10-15', '2023-12-15'),
('PRESC015', 'PROC015', 'H005', 'PROV001', 'DOC002', '2023-10-15 12:30:00', '2023-10-15', NULL),
('PRESC016', 'PROC016', 'H006', 'PROV001', 'DOC002', '2023-10-15 13:00:00', '2023-10-15', '2024-01-15'),
('PRESC017', 'PROC017', 'H007', 'PROV001', 'DOC002', '2023-10-15 13:30:00', '2023-10-15', '2023-12-15'),
('PRESC018', 'PROC018', 'H008', 'PROV001', 'DOC002', '2023-10-16 10:30:00', '2023-10-16', NULL),
('PRESC019', 'PROC019', 'H009', 'PROV001', 'DOC002', '2023-10-16 11:00:00', '2023-10-16', NULL),
('PRESC020', 'PROC020', 'H010', 'PROV001', 'DOC002', '2023-10-16 11:30:00', '2023-10-16', NULL);
-- Insert prescribed medicines
INSERT INTO prescribed_medicines (prescribed_id, prescription_id, medicine_name, dosage, duration, notes)
VALUES
('PMED001', 'PRESC001', 'Lisinopril', '10mg daily', '2 months', 'For blood pressure'),
('PMED002', 'PRESC001', 'Hydrochlorothiazide', '12.5mg daily', '2 months', 'Diuretic'),
('PMED003', 'PRESC002', 'Atorvastatin', '20mg at bedtime', '3 months', 'For cholesterol'),
('PMED004', 'PRESC003', 'Metformin', '500mg twice daily', '2 months', 'For diabetes'),
('PMED005', 'PRESC003', 'Insulin Glargine', '20 units at bedtime', '2 months', 'Long-acting insulin'),
('PMED006', 'PRESC004', 'Clopidogrel', '75mg daily', '1 month', 'Blood thinner'),
('PMED007', 'PRESC005', 'Metoprolol', '50mg twice daily', '3 months', 'Beta blocker'),
('PMED008', 'PRESC006', 'Albuterol inhaler', '2 puffs every 4-6 hours', '2 months', 'For asthma'),
('PMED009', 'PRESC006', 'Fluticasone inhaler', '2 puffs daily', '2 months', 'Steroid inhaler'),
('PMED010', 'PRESC007', 'Furosemide', '40mg daily', '4 months', 'Diuretic'),
('PMED011', 'PRESC007', 'Lisinopril', '10mg daily', '4 months', 'ACE inhibitor'),
('PMED012', 'PRESC008', 'Nitroglycerin', '0.4mg as needed', '2 months', 'For chest pain'),
('PMED013', 'PRESC009', 'Cilostazol', '100mg twice daily', '3 months', 'For PAD'),
('PMED014', 'PRESC010', 'Apixaban', '5mg twice daily', '2 months', 'Blood thinner'),
('PMED015', 'PRESC013', 'Amoxicillin', '500mg three times daily', '10 days', 'For ear infection'),
('PMED016', 'PRESC014', 'Cetirizine', '10mg daily', '2 months', 'For allergies'),
('PMED017', 'PRESC016', 'Methylphenidate', '10mg twice daily', '3 months', 'For ADHD'),
('PMED018', 'PRESC017', 'Montelukast', '5mg at bedtime', '2 months', 'For asthma');

-- Insert prescribed tests
INSERT INTO prescribed_tests (test_id, prescription_id, test_name, test_date, result_summary)
VALUES
('PTEST001', 'PRESC001', 'Blood pressure monitoring', '2023-11-15', 'Improved to 120/80'),
('PTEST002', 'PRESC002', 'Lipid panel', '2023-11-15', 'LDL reduced to 100'),
('PTEST003', 'PRESC003', 'HbA1c test', '2023-12-15', 'Reduced to 6.5%'),
('PTEST004', 'PRESC004', 'Stress test', '2023-11-15', 'Positive for ischemia'),
('PTEST005', 'PRESC005', 'EKG', '2023-11-15', 'Normal sinus rhythm'),
('PTEST006', 'PRESC006', 'Pulmonary function test', '2023-11-15', 'Mild obstruction'),
('PTEST007', 'PRESC007', 'Echocardiogram', '2023-12-15', 'EF 45%'),
('PTEST008', 'PRESC008', 'Cardiac catheterization', '2023-11-16', '70% blockage in LAD'),
('PTEST009', 'PRESC009', 'Ankle-brachial index', '2023-11-16', '0.8 right leg'),
('PTEST010', 'PRESC010', 'Holter monitor', '2023-11-16', 'Occasional AFib'),
('PTEST011', 'PRESC013', 'Ear culture', '2023-10-20', 'Positive for S. pneumoniae'),
('PTEST012', 'PRESC014', 'Skin prick test', '2023-10-20', 'Positive for grass pollen'),
('PTEST013', 'PRESC016', 'ADHD rating scale', '2024-01-15', 'Improved symptoms');
-- Insert claims
INSERT INTO Claims (claim_id, subscribe_id, procedure_id, provider_id, h_id, claim_status, claim_date, amount_claimed, amount_approved)
VALUES
('CLAIM001', 'SUB001', 'PROC001', 'PROV001', 'H001', 'APPROVED', '2023-10-16', 5000, 4500),
('CLAIM002', 'SUB002', 'PROC001', 'PROV001', 'H001', 'APPROVED', '2023-10-16', 5000, 5000),
('CLAIM003', 'SUB021', 'PROC011', 'PROV001', 'H001', 'APPROVED', '2023-10-16', 3000, 3000),
('CLAIM004', 'SUB022', 'PROC011', 'PROV001', 'H001', 'DENIED', '2023-10-16', 3000, 0),
('CLAIM005', 'SUB004', 'PROC002', 'PROV001', 'H002', 'APPROVED', '2023-10-16', 4500, 4000),
('CLAIM006', 'SUB005', 'PROC002', 'PROV001', 'H002', 'APPROVED', '2023-10-16', 4500, 4500),
('CLAIM007', 'SUB025', 'PROC012', 'PROV001', 'H002', 'APPROVED', '2023-10-16', 2000, 2000),
('CLAIM008', 'SUB007', 'PROC003', 'PROV001', 'H003', 'PENDING', '2023-10-16', 6000, 0),
('CLAIM009', 'SUB008', 'PROC003', 'PROV001', 'H003', 'APPROVED', '2023-10-16', 6000, 5500),
('CLAIM010', 'SUB029', 'PROC013', 'PROV001', 'H003', 'APPROVED', '2023-10-16', 2500, 2500),
('CLAIM011', 'SUB010', 'PROC004', 'PROV001', 'H004', 'APPROVED', '2023-10-16', 8000, 7500),
('CLAIM012', 'SUB011', 'PROC004', 'PROV001', 'H004', 'APPROVED', '2023-10-16', 8000, 8000),
('CLAIM013', 'SUB032', 'PROC014', 'PROV001', 'H004', 'DENIED', '2023-10-16', 3500, 0),
('CLAIM014', 'SUB013', 'PROC005', 'PROV001', 'H005', 'APPROVED', '2023-10-16', 5500, 5000),
('CLAIM015', 'SUB014', 'PROC005', 'PROV001', 'H005', 'APPROVED', '2023-10-16', 5500, 5500),
('CLAIM016', 'SUB035', 'PROC015', 'PROV001', 'H005', 'APPROVED', '2023-10-16', 1800, 1800),
('CLAIM017', 'SUB016', 'PROC006', 'PROV001', 'H006', 'APPROVED', '2023-10-16', 4000, 4000),
('CLAIM018', 'SUB017', 'PROC006', 'PROV001', 'H006', 'DENIED', '2023-10-16', 4000, 0),
('CLAIM019', 'SUB038', 'PROC016', 'PROV001', 'H006', 'APPROVED', '2023-10-16', 3000, 3000),
('CLAIM020', 'SUB019', 'PROC007', 'PROV001', 'H007', 'APPROVED', '2023-10-16', 7000, 6500);

-- Insert claim history
INSERT INTO Claim_history (claim_history_id, claim_id, description, action_date)
VALUES
('CHIST001', 'CLAIM001', 'Claim submitted', '2023-10-16 09:00:00'),
('CHIST002', 'CLAIM001', 'Initial review completed', '2023-10-17 10:00:00'),
('CHIST003', 'CLAIM001', 'Approved with partial payment', '2023-10-18 11:00:00'),
('CHIST004', 'CLAIM002', 'Claim submitted', '2023-10-16 09:05:00'),
('CHIST005', 'CLAIM002', 'Approved for full amount', '2023-10-17 10:30:00'),
('CHIST006', 'CLAIM003', 'Claim submitted', '2023-10-16 09:10:00'),
('CHIST007', 'CLAIM003', 'Approved for full amount', '2023-10-17 09:30:00'),
('CHIST008', 'CLAIM004', 'Claim submitted', '2023-10-16 09:15:00'),
('CHIST009', 'CLAIM004', 'Denied - procedure not covered', '2023-10-18 14:00:00'),
('CHIST010', 'CLAIM005', 'Claim submitted', '2023-10-16 09:20:00'),
('CHIST011', 'CLAIM005', 'Approved with partial payment', '2023-10-18 10:00:00'),
('CHIST012', 'CLAIM006', 'Claim submitted', '2023-10-16 09:25:00'),
('CHIST013', 'CLAIM006', 'Approved for full amount', '2023-10-17 11:00:00'),
('CHIST014', 'CLAIM007', 'Claim submitted', '2023-10-16 09:30:00'),
('CHIST015', 'CLAIM007', 'Approved for full amount', '2023-10-17 11:30:00'),
('CHIST016', 'CLAIM008', 'Claim submitted', '2023-10-16 09:35:00'),
('CHIST017', 'CLAIM009', 'Claim submitted', '2023-10-16 09:40:00'),
('CHIST018', 'CLAIM009', 'Approved with partial payment', '2023-10-18 15:00:00'),
('CHIST019', 'CLAIM010', 'Claim submitted', '2023-10-16 09:45:00'),
('CHIST020', 'CLAIM010', 'Approved for full amount', '2023-10-17 16:00:00');
-- Insert payment history
INSERT INTO Payment_history (h_id, provider_id, amount, payment_method, payment_status, payment_date, remarks)
VALUES
('H001', 'PROV001', 500, 'CREDIT_CARD', 'completed', '2023-10-15 10:45:00', 'Co-payment for procedure'),
('H002', 'PROV001', 500, 'DEBIT_CARD', 'completed', '2023-10-15 11:15:00', 'Co-payment for procedure'),
('H003', 'PROV001', 500, 'CREDIT_CARD', 'completed', '2023-10-15 11:45:00', 'Co-payment for procedure'),
('H004', 'PROV001', 1000, 'DEBIT_CARD', 'completed', '2023-10-15 12:15:00', 'Co-payment for procedure'),
('H005', 'PROV001', 500, 'CREDIT_CARD', 'completed', '2023-10-15 12:45:00', 'Co-payment for procedure'),
('H006', 'PROV001', 500, 'DEBIT_CARD', 'completed', '2023-10-15 13:15:00', 'Co-payment for procedure'),
('H007', 'PROV001', 1000, 'CREDIT_CARD', 'completed', '2023-10-15 13:45:00', 'Co-payment for procedure'),
('H008', 'PROV001', 500, 'DEBIT_CARD', 'completed', '2023-10-16 10:45:00', 'Co-payment for procedure'),
('H009', 'PROV001', 500, 'CREDIT_CARD', 'completed', '2023-10-16 11:15:00', 'Co-payment for procedure'),
('H010', 'PROV001', 500, 'DEBIT_CARD', 'completed', '2023-10-16 11:45:00', 'Co-payment for procedure'),
('H001', 'PROV001', 300, 'CREDIT_CARD', 'completed', '2023-10-15 10:45:00', 'Co-payment for pediatric visit'),
('H002', 'PROV001', 300, 'DEBIT_CARD', 'completed', '2023-10-15 11:15:00', 'Co-payment for pediatric visit'),
('H003', 'PROV001', 300, 'CREDIT_CARD', 'completed', '2023-10-15 11:45:00', 'Co-payment for pediatric visit'),
('H004', 'PROV001', 300, 'DEBIT_CARD', 'completed', '2023-10-15 12:15:00', 'Co-payment for pediatric visit'),
('H005', 'PROV001', 300, 'CREDIT_CARD', 'completed', '2023-10-15 12:45:00', 'Co-payment for pediatric visit'),
('H006', 'PROV001', 300, 'DEBIT_CARD', 'completed', '2023-10-15 13:15:00', 'Co-payment for pediatric visit'),
('H007', 'PROV001', 300, 'CREDIT_CARD', 'completed', '2023-10-15 13:45:00', 'Co-payment for pediatric visit'),
('H008', 'PROV001', 300, 'DEBIT_CARD', 'completed', '2023-10-16 10:45:00', 'Co-payment for pediatric visit'),
('H009', 'PROV001', 300, 'CREDIT_CARD', 'completed', '2023-10-16 11:15:00', 'Co-payment for pediatric visit'),
('H010', 'PROV001', 300, 'DEBIT_CARD', 'completed', '2023-10-16 11:45:00', 'Co-payment for pediatric visit');
