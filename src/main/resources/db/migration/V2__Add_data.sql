INSERT INTO usr (id, username, password, first_name, last_name, registration_date,/* token,*/ email, role_id, status_id,
                 company_id, notification_type_id, location_id)
VALUES (1, 'admin', 'admin', 'Aa', 'Bb', '2020-01-01 23:59:59', /*NULL,*/ 'president@email.com', 1, 1, NULL, 1, NULL);

INSERT INTO payment (id, city, country, pay_date, left_pay, method, name, number, status, sum,
                     type, payment_user_id)
VALUES (1, 'Tirane', 'AL', '2020-09-24 04:36', '847.71', 'PayPal', 'The Smoking Jug', 'ex***@exam.com', 'success',
        '105.15', 0, 1),
       (2, 'Vienna', 'AT', '2020-09-21 05:29', '3202.83', 'Visa', 'The Waving Pot', '4024...7086', 'failed',
        '253.24', 1, 1),
       (3, 'Minsk', 'BY', '2020-09-20 21:20', '471.64', 'MasterCard', 'The Dry Carriage Outfitter', '5170...3971', 'success',
        '328.90', 0, 1);
