SHOW TABLES;
SELECT * FROM cards;

SELECT * FROM pronunciation;
INSERT INTO pronunciation (id, transcription) VALUES
(0, 'none'),
(1, 'beə(r)'),
(2, '''kæri'),
(3, 'ɪ''nɔ(r)ːməs'),
(4, 'help'),
(5, 'kæn'),
(6, 'streɪn');

INSERT INTO cards (
id, content,
definition,
lang, type, pronunciation_id)
VALUES (
-- 2
1, 'bear',
'to accept, tolerate, or endure something, especially something unpleasant.',
0, 1, 1), (
-- 3
2, 'bear',
'to have or continue to have something.',
0, 1, 1), (
-- 4
3, 'bear',
'a large, strong wild mammal with a thick fur coat that lives especially in colder parts of Europe, Asia, and North America.',
0, 0, 1), (
-- 5
4, 'carry',
'to hold something or someone with your hands, arms, or on your back and transport them from one place to another.',
0, 1, 2), (
-- 6
5, 'enormous',
'extremely large',
0, 2, 3), (
-- 7
6, 'help',
'make it easier for sb to do sth by offering one''s services or resources.',
0, 1, 4), (
-- 8
7, 'can',
'to be able to or to be allowed to do sth.',
0, 1, 5), (
-- 9
8, 'can',
'a closed metal container, especially cylinder-shaped, in which some types of drink and food are sold.',
0, 0, 5), (
-- 10
 9, 'strain',
'something that makes you feel nervous and worried.',
0, 0, 6);

SELECT * FROM samples;
INSERT INTO samples (id, content) VALUES
(1, 'The strain must have been enormous but she bore it well.'),
(2, 'Tell me now! I can''t bear the suspense!'),
(3, 'Thank you for your advice - I''ll bear it in mind.'),
(4, 'He bore the name of the Greek god.'),
(5, 'Russia is only a country where bears leave among citizens'),
(6, 'The reduction of polar bears'' population is observed.'),
(7, 'Can you help me carry this table?'),
(8, 'I can''t carry objects from one place to another.'),
(9, 'You made enormous amount of efforts to help.'),
(10, 'He ate the whole can of beans'),
(11, 'Cans prevent food spoilage.'),
(12, 'She is still not ready to face the strains of a job.');

SELECT * FROM cards_samples;
INSERT INTO cards_samples (Card_id, samples_id) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 6),
(4, 7), (4, 8),
(5, 1), (5, 9),
(6, 7), (6, 9),
(7, 2), (7, 7),
(8, 10), (8, 11),
(9, 1), (9, 12);

INSERT INTO cards (id, content, definition, lang, type, pronunciation_id)
VALUES
(10, 'спправляться с', '-', 1, 1, 0),
(11, 'иметь', '-', 1, 1, 0),
(12, 'медведь', '-', 1, 0, 0),
(13, 'переносить', '-', 1, 1, 0),
(14, 'громадный', '-', 1, 2, 0),
(15, 'помогать', '-', 1, 1, 0),
(16, 'мочь', '-', 1, 1, 0),
(17, 'жестяная банка', '-', 1, 0, 0),
(18, 'напряжение', '-', 1, 0, 0),
(19, 'расширение', '-', 1, 0, 0);

SELECT * FROM card_mappings;
INSERT INTO card_mappings (id, frequency, dest_card_id, source_card_id)
VALUES
(1, 1, 24, 19), (2, 1, 19, 24),
(3, 1, 1, 10), (4, 1, 10, 1),
(5, 1, 2, 11), (6, 1, 11, 2),
(7, 1, 3, 12), (8, 1, 12, 3),
(9, 1, 4, 13), (10, 1, 13, 4),
(11, 1, 5, 14), (12, 1, 14, 5),
(13, 1, 6, 15), (14, 1, 15, 6),
(15, 1, 7, 16), (16, 1, 16, 7),
(17, 1, 8, 17), (18, 1, 17, 8),
(19, 1, 9, 18), (20, 1, 18, 9);