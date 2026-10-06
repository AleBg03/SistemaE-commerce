USE ecommerce;

INSERT INTO utente_registrato (tipo_utente, email, password, ruolo, admin_id)
SELECT 'Amministratore', 'admin@email.com', 'AdminPass123!', 2, 1 FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM utente_registrato WHERE email = 'admin@email.com');

INSERT INTO categoria (nome, descrizione)
SELECT 'Elettronica', 'Dispositivi e accessori elettronici' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE nome = 'Elettronica');
INSERT INTO categoria (nome, descrizione)
SELECT 'Casa', 'Articoli per la casa' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE nome = 'Casa');
INSERT INTO categoria (nome, descrizione)
SELECT 'Abbigliamento', 'Vestiti e accessori' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE nome = 'Abbigliamento');

SET @elettronica = (SELECT id FROM categoria WHERE nome = 'Elettronica');
SET @casa = (SELECT id FROM categoria WHERE nome = 'Casa');
SET @abbigliamento = (SELECT id FROM categoria WHERE nome = 'Abbigliamento');

INSERT INTO prodotto (nome, descrizione, prezzo, qtaDisponibile, scontato, disponibile, percentualeSconto, categoria_id)
SELECT 'Mouse ottico', 'Mouse USB 1600dpi', 19.99, 50, 0, 1, 0, @elettronica FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM prodotto WHERE nome = 'Mouse ottico');
INSERT INTO prodotto (nome, descrizione, prezzo, qtaDisponibile, scontato, disponibile, percentualeSconto, categoria_id)
SELECT 'Cuffie BT', 'Cuffie bluetooth', 49.90, 5, 0, 1, 0, @elettronica FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM prodotto WHERE nome = 'Cuffie BT');
INSERT INTO prodotto (nome, descrizione, prezzo, qtaDisponibile, scontato, disponibile, percentualeSconto, categoria_id)
SELECT 'Lampada', 'Lampada da tavolo', 25.00, 10, 0, 1, 0, @casa FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM prodotto WHERE nome = 'Lampada');
INSERT INTO prodotto (nome, descrizione, prezzo, qtaDisponibile, scontato, disponibile, percentualeSconto, categoria_id)
SELECT 'Felpa', 'Felpa in cotone', 39.90, 0, 0, 0, 0, @abbigliamento FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM prodotto WHERE nome = 'Felpa');

INSERT INTO utente_registrato (tipo_utente, email, password, ruolo, nome, cognome)
SELECT 'Cliente', 'mario.rossi@email.com', 'Password123!', 1, 'Mario', 'Rossi' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM utente_registrato WHERE email = 'mario.rossi@email.com');
INSERT INTO carrello (cliente_email)
SELECT 'mario.rossi@email.com' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM carrello WHERE cliente_email = 'mario.rossi@email.com');
INSERT INTO indirizzo (via, civico, cap, citta, cliente_email)
SELECT 'Via Toledo', '15', 80134, 'Napoli', 'mario.rossi@email.com' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM indirizzo WHERE cliente_email = 'mario.rossi@email.com');

SET @indirizzo = (SELECT id FROM indirizzo WHERE cliente_email = 'mario.rossi@email.com' ORDER BY id LIMIT 1);
SET @mouse = (SELECT id FROM prodotto WHERE nome = 'Mouse ottico');
SET @lampada = (SELECT id FROM prodotto WHERE nome = 'Lampada');
SET @senza_ordini = (SELECT COUNT(*) = 0 FROM ordine WHERE cliente_email = 'mario.rossi@email.com');

INSERT INTO ordine (cliente_email, indirizzo_id, dataCreazione, stato, totComplessivo)
SELECT 'mario.rossi@email.com', @indirizzo, NOW(6) - INTERVAL 3 DAY, 'CONSEGNATO', 25.00 FROM DUAL WHERE @senza_ordini;
SET @ordine_consegnato = LAST_INSERT_ID();
INSERT INTO rigaordine (ordine_id, prodotto_id, quantita, prezzoUnitario)
SELECT @ordine_consegnato, @lampada, 1, 25.00 FROM DUAL WHERE @senza_ordini;

INSERT INTO ordine (cliente_email, indirizzo_id, dataCreazione, stato, totComplessivo)
SELECT 'mario.rossi@email.com', @indirizzo, NOW(6), 'INSERITO', 39.98 FROM DUAL WHERE @senza_ordini;
SET @ordine_inserito = LAST_INSERT_ID();
INSERT INTO rigaordine (ordine_id, prodotto_id, quantita, prezzoUnitario)
SELECT @ordine_inserito, @mouse, 2, 19.99 FROM DUAL WHERE @senza_ordini;
