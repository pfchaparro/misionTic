# Valor total del dinero, sumando bolsillos y billetera principal
SELECT wal.user_id AS id_user, u.name AS name_user, 
SUM(wal.amount) AS total_wallet,
SUM(poc.amount_pocket) AS total_pocket,
SUM(wal.amount + poc.amount_pocket) AS total
FROM wallet wal
INNER JOIN user u ON u.id = wal.user_id
INNER JOIN pocket poc ON poc.wallet_id = wal.id
GROUP BY wal.user_id;

#Valor total de la billetera
SELECT wal.user_id AS id_user, u.name AS name_user, SUM(wal.amount) AS total_wallet
FROM wallet wal
INNER JOIN user u ON u.id = wal.user_id
GROUP BY wal.user_id;

# Valor total de los bolsillos (Valor por cada bolsillo).
SELECT wal.user_id, u.name AS name_user, poc.name AS name_pocket, SUM(poc.amount_pocket) AS total_pocket FROM pocket poc
INNER JOIN wallet wal ON wal.id = poc.wallet_id
INNER JOIN user u ON u.id = wal.user_id
GROUP BY wal.user_id, poc.name;

# Log de transacciones de registros en la billetera principal. Limitado a 20
# resultados. Y paginado iniciando en Cero, Para el caso de ir
# aumentando paginas para mostrarle al usuario el registro total.
SELECT wallet_log.id as id, transaction_type.name AS type_transaction, wallet_id, amount 
FROM wallet_log, transaction_type 
WHERE transaction_type_id = transaction_type.id 
GROUP BY wallet_log.id  
LIMIT 20 
OFFSET 0; 

# Log de transacciones de registros en el bolsillo, query sin límite y
# basado en el id del bolsillo
SELECT pl.id, pl.amount, tp.name AS type_transaction, pl.pocket_id, pl.timestamp
FROM pocket_log pl
INNER JOIN transaction_type tp ON tp.id = pl.transaction_type_id

# Reporte de usuarios, valor total de dinero en bolsillos y billetera.
SELECT wal.user_id AS id_user, u.name AS name_user, 
SUM(wal.amount) AS total_wallet,
SUM(poc.amount_pocket) AS total_pocket
FROM wallet wal
INNER JOIN user u ON u.id = wal.user_id
INNER JOIN pocket poc ON poc.wallet_id = wal.id
GROUP BY wal.user_id;

# Top 10 de mejores clientes.
SELECT wal.user_id AS id_user, u.name AS name_user, u.document,
SUM(wal.amount + poc.amount_pocket) AS total
FROM wallet wal
INNER JOIN user u ON u.id = wal.user_id
INNER JOIN pocket poc ON poc.wallet_id = wal.id
WHERE transaction_type_id = 4
GROUP BY wal.user_id
ORDER BY total DESC
LIMIT 10;

# Top 10 de clientes que realizan mas pagos por la aplicación.
SELECT count(quer.user_id) AS id_user, u.name, u.document, sum(quer.amount) amount_total, quer.transaction_type_id FROM(
	SELECT w.user_id AS user_id, wl.id AS id, wl.amount AS amount, wl.transaction_type_id AS transaction_type_id, wl.timestamp AS date 
	FROM wallet_log wl
	INNER JOIN wallet w ON w.id = wl.wallet_id
	UNION all
	SELECT w.user_id AS user_id, pl.id AS id, pl.amount AS amount, pl.transaction_type_id AS transaction_type_id, pl.timestamp AS date 
	FROM pocket_log pl
	INNER JOIN pocket p ON p.id = pl.pocket_id
	INNER JOIN wallet w ON w.id = p.wallet_id
) AS quer
INNER JOIN user u ON  u.id = quer.user_id
WHERE quer.transaction_type_id = 4
GROUP BY quer.user_id
ORDER BY quer.user_id DESC

#Top 10 de clientes que usan más los bolsillos.
SELECT u.name,u.lastname,u.document, COUNT(pl.id) as cantidad 
FROM pocket_log pl 
LEFT JOIN pocket p ON p.id = pl.pocket_id
LEFT JOIN wallet w ON w.id = p.wallet_id
LEFT JOIN user u ON u.id = w.user_id 
GROUP BY wallet_id
ORDER BY cantidad DESC
LIMIT 10;

#Top 10 de clientes con mas transacciones en la billetera
SELECT u.name,u.lastname,u.document, COUNT(wl.id) as cantidad 
FROM wallet_log wl
LEFT JOIN wallet w ON w.id = wl.wallet_id
LEFT JOIN user u ON u.id = w.user_id 
GROUP BY wallet_id
ORDER BY cantidad DESC
LIMIT 10;