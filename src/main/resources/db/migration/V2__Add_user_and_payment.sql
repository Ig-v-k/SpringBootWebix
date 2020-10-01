INSERT INTO usr (id, username, password, first_name, last_name, registration_date, token, email, role_id, status_id,
                 company_id, notification_type_id, location_id)
VALUES (1, 'admin', 'admin', 'Aa', 'Bb', '2020-01-01 23:59:59', NULL, 'president@email.com', 1, 1, NULL, 1, NULL);

INSERT INTO payment (id, status, date, method, number, type, sum, left_pay, name, city,
                 country, payments_user)
VALUES (1, 'success', '2018-09-24 04:36', 'PayPal', 'example.one@exam.com', 0, '105.15', '847.71', 'The Smoking Jug', 'Tirane',
        'AL', usr.id == 1);
