CREATE DATABASE aveng;

USE aveng;

DROP TABLE examples;
DROP TABLE card_mapping;
DROP TABLE cards;
DROP TABLE categories;
DROP TABLE transcriptions;
DROP TABLE languages;

CREATE TABLE languages
(
    id INT NOT NULL AUTO_INCREMENT,
    lang CHAR(2) NOT NULL,
    supported BOOLEAN NOT NULL,
    CONSTRAINT pk_languages PRIMARY KEY (id)
);

CREATE TABLE transcriptions
(
    id INT NOT NULL AUTO_INCREMENT,
    transcription VARCHAR(50) NOT NULL,
    CONSTRAINT pk_transcriptions PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE cards
(
    id INT NOT NULL AUTO_INCREMENT,
    content VARCHAR(50) NOT NULL,
    definition VARCHAR(150) NOT NULL,
    category_id INT NOT NULL,
    lang_id INT NOT NULL,
    transcription_id INT NOT NULL,
    CONSTRAINT pk_aveng PRIMARY KEY (id),
    CONSTRAINT fk_lang_id_languages FOREIGN KEY (lang_id) REFERENCES languages (id),
    CONSTRAINT fk_transcription_id_transcriptions FOREIGN KEY (transcription_id) REFERENCES transcriptions (id)
);

CREATE TABLE card_mapping
(
    source_card_id INT NOT NULL,
    dest_card_id INT NOT NULL,
    frequency INT NOT NULL,
    CONSTRAINT fk_source_card_id_cards FOREIGN KEY (source_card_id) REFERENCES cards (id),
    CONSTRAINT fk_dest_card_id_cards FOREIGN KEY (dest_card_id) REFERENCES cards (id)
);

CREATE TABLE examples
(
    id INT NOT NULL AUTO_INCREMENT,
    example VARCHAR(150) NOT NULL,
    card_id INT NOT NULL,
    CONSTRAINT pk_examples PRIMARY KEY (id),
    CONSTRAINT fk_card_id_cards FOREIGN KEY (card_id) REFERENCES cards (id)
);

INSERT INTO languages
(
    lang,
    supported
)
VALUES
(
    'EN',
    TRUE
),
(
    'RU',
    TRUE
),
(
    'DE',
    TRUE
);

INSERT INTO transcriptions
(transcription)
VALUES
-- 1
('ɪk''stenʃ(ə)n'),
-- 2
('раш:ыр''эн''ий''э'),
-- 3
('beə(r)'),
-- 4
('п''ир''инас''ит'''),
-- 5
('м''эдв''эд'''),
-- 6
('''kæri'),
-- 7
('bɛːɐ̯,'),
-- 8
('им''эт''');

INSERT INTO categories
(name)
VALUES
-- Русский
('существительное'),			-- 1
('глагол'),						-- 2
('прилагательное'),				-- 3
('числительное'),				-- 4
('местоимение'),				-- 5
('наречие'),					-- 6
('причастие'),					-- 7
('деепричастие'),				-- 8
('предлог'),					-- 9
('частица'),					-- 10
('союз'),						-- 11
('междометие'),					-- 12
-- English
('noun'),						-- 13
('pronoun'),					-- 14
('verb'),						-- 15
('adjective'),					-- 16
('adverb'),						-- 17
('number'),						-- 18
('preposition'),				-- 19
('conjunction'),				-- 20
('interjection'),				-- 21
('article'),					-- 22
-- German
('verb'), 						-- 23 verb
('nomen'), 						-- 24 noun
('artikel'),					-- 25 article
('pronomen'), 					-- 26 pronoun
('numerale'), 					-- 27 number
('adjektiv'), 					-- 28 adjective
('adverb'), 					-- 29 adverb
('präposition'), 				-- 30 preposition
('konjunktion'),	 			-- 31 conjunction
('interjektion'); 				-- 32 interjection

INSERT INTO cards
(
    content,
    definition,
    category_id,
    lang_id,
    transcription_id
)
VALUES
(
    -- 1
    'extension',
    'the last part of the name of a computer file, which comes after a dot and shows what type of file it is.',
    13,
    1,
    1
),(
    -- 2
    'расширение',
    'the last part of the name of a computer file, which comes after a dot and shows what type of file it is.',
    1,
    2,
    2
),(
    -- 3
    'bear',
    'to accept, tolerate, or endure something, especially something unpleasant.',
    15,
    1,
    3
),(
    -- 4
    'bear',
    'to have or continue to have something.',
    15,
    1,
    3
),(
    -- 5
    'переносить',
    'испытывать какие-либо негативные ощущения, опыт, оставаясь в живых и без значительных негативных последствий.',
    2,
    2,
    4
),(
    -- 6
    'переносить',
    'перемещать что-либо с места на место, на некоторое расстояние с отрывом от поверхности.',
    2,
    2,
    4
),(
    -- 7
    'bear',
    'a large, strong wild mammal with a thick fur coat that lives especially in colder parts of Europe, Asia, and North America.',
    13,
    1,
    3
),(
    -- 8
    'медведь',
    'крупное хищное млекопитающее животное с длинной шерстью и толстыми ногами.',
    1,
    2,
    5
),(
    -- 9
    'carry',
    'to hold something or someone with your hands, arms, or on your back and transport them from one place to another.',
    15,
    1,
    6
),(
    -- 10
    'bär',
    'großes Raubtier mit dickem Pelz, gedrungenem Körper und kurzem Schwanz.',
    24,
    3,
    7
),(
    -- 11
    'иметь',
    'обладать свойством или владеть имуществом.',
    24,
    2,
    8
);

INSERT INTO examples
(example, card_id)
VALUES
('Most applications provide extensions for the files they create.', 1),
('Relational database scripts have to be stored in the file with ".sql" extension.', 1),
('Расширение помогает системе понять, какой программой открывать файлы.', 2),
('Некоторые расширения могут указывать на способ кодирования.', 2),
('The strain must have been enormous but she bore it well.', 3),
('Tell me now! I can''t bear the suspense!', 3),
('Thank you for your advice - I''ll bear it in mind', 4),
('He bore the name of the Greek god.', 4),
('Он умел, конечно, переносить боль и терпеть ее, но что значит — терпеть?', 5),
('Пациент успешно перенес операцию.', 5),
('Женя принялась переносить еду и посуду в комнату.', 6),
('Он попросил меня перенести чемоданы из спальни в зал.', 6),
('Russia is only a country where bears leave among citizens', 7),
('The reduction of polar bears'' population is observed.', 7),
('Медведи зимой впадают в спячку.', 8),
('Почему медведя называют косолапым?', 8),
('Can you help me carry this table?', 9),
('I can''t carry objects from one place to another.', 9),
('Der Bär gilt als blutrünstiges Raubtier, ist aber ein Allesfresser', 10),
('Dort oben siehst du den großen Bären!', 10),
('Иван Ильич имел в Симбирске дом.', 11),
('Он имел надежных друзей.', 11);

INSERT INTO aveng.card_mapping
(source_card_id, dest_card_id, frequency)
VALUES
(1, 2, 0.9),
(2, 1, 0.8),
(3, 5, 0.6),
(4, 11, 0.9),
(5, 3, 0.6),
(6, 9, 1),
(7, 8, 1),
(7, 10, 0.3),
(8, 7, 1),
(8, 10, 1),
(9, 6, 0.7),
(10, 8, 1),
(10, 7, 0.3),
(11, 4, 0.9);

SELECT
    c1.content,
    tr.transcription,
    c1.definition,
    c2.content,
    ex.example
FROM cards AS c1
         INNER JOIN transcriptions AS tr ON c1.transcription_id = tr.id
         INNER JOIN card_mapping AS cm ON c1.id = cm.source_card_id
         INNER JOIN cards AS c2 ON c2.id = cm.dest_card_id
         LEFT JOIN examples AS ex ON c1.id = ex.card_id
WHERE c1.lang_id = (SELECT id from languages WHERE lang = 'EN');