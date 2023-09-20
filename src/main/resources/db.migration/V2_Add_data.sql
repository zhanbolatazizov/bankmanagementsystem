INSERT INTO User (name, surname)
VALUES ('John', 'Snow');

INSERT INTO Account (number, balance, productLimit, serviceLimit, monthlySpent, category, limitSetDate, currency, user_id)
VALUES ('123456789', 1000.0, 500.0, 500.0, 0.0, 'PRODUCT', '2023-09-19', 'USD', 1);

INSERT INTO Transaction (amount, fromAccountNumber, toAccountNumber, category, limit_exceeded, createDate, currency)
VALUES (100.0, '123456789', '987654321', 'PRODUCT', FALSE, '2023-09-19 14:30:00', 'USD');