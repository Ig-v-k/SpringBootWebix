INSERT INTO usr (id, username, password, first_name, last_name, registration_date, token, email, role_id, status_id,
                 company_id, notification_type_id, location_id)
VALUES (1, 'admin', 'admin', 'Aa', 'Bb', '2020-01-01 23:59:59', NULL, 'president@email.com', 1, 1, NULL, 1, NULL);

INSERT INTO payment (id, city, country, registration_date, left_pay, method, name, number, status, sum,
                 type, payments_user_id)
VALUES (1, 'The Smoking Jug', 'Tirane', '2018-09-24 04:36', '847.71', 'PayPal', 'The Smoking Jug', 'example@exam.com', 'success', '105.15',
        0, 1);
