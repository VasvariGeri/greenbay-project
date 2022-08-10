INSERT INTO users (id, username, password, roles, is_active, green_bay_dollar)
VALUES (DEFAULT, 'Dani', 'Dani', 'User', 1, 10);
INSERT INTO users (id, username, password, roles, is_active, green_bay_dollar)
VALUES (DEFAULT, 'Balint', 'Balint', 'User', 1, 20);
INSERT INTO users (id, username, password, roles, is_active, green_bay_dollar)
VALUES (DEFAULT, 'Balazs', 'Balazs', 'User', 1, 30);

INSERT INTO items (id, name, description, url, starting_price, purchase_price, is_sold, user_id)
VALUES (DEFAULT, 'kocsi', 'leiras', 'https://www.google.com', 1, 5, 0, 1);
INSERT INTO items (id, name, description, url, starting_price, purchase_price, is_sold, user_id)
VALUES (DEFAULT, 'bicikli', 'leiras', 'https://www.google.com', 2, 7, 0, 2);
INSERT INTO items (id, name, description, url, starting_price, purchase_price, is_sold, user_id)
VALUES (DEFAULT, 'tank', 'leiras', 'https://www.google.com', 3, 9, 0, 3);

INSERT INTO bids (id, price, item_id, user_id)
VALUES (DEFAULT, 2, 1, 2);
INSERT INTO bids (id, price, item_id, user_id)
VALUES (DEFAULT, 3, 2, 3);
INSERT INTO bids (id, price, item_id, user_id)
VALUES (DEFAULT, 4, 3, 1);