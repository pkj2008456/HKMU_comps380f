INSERT INTO USER_ACCT (USERNAME, DELIVERY_ADDR, EMAIL_ADDR, FULL_NAME, PASSWORD) VALUES ('admin1', 'Kai Tak Coml Bldg, Western District, Hong Kong', 'ben@book.com', 'Ben Ng', '{noop}admin1pw');
INSERT INTO USER_ACCT (USERNAME, DELIVERY_ADDR, EMAIL_ADDR, FULL_NAME, PASSWORD) VALUES ('admin2', 'O/S 154 Des Voeux Rd C, Sheung Wan, Hong Kong', 'keith@book.com', 'Keith Lee', '{noop}admin2pw');
INSERT INTO USER_ACCT (USERNAME, DELIVERY_ADDR, EMAIL_ADDR, FULL_NAME, PASSWORD) VALUES ('user1', 'Superluck Ind Centre, Tsuen Wan District, Hong Kong', 'apple@google.com', 'Apple Chan', '{noop}user1pw');
INSERT INTO USER_ACCT (USERNAME, DELIVERY_ADDR, EMAIL_ADDR, FULL_NAME, PASSWORD) VALUES ('user2', 'Times Tower Cheung Sha Wan, Sham Shui Po District, Hong Kong', 'may@google.com', 'May Yan', '{noop}user2pw');

INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('ROLE_USER', 'admin1');
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('ROLE_ADMIN', 'admin1');
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('ROLE_USER', 'admin2');
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('ROLE_ADMIN', 'admin2');
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('ROLE_USER', 'user1');
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('ROLE_USER', 'user2');

INSERT INTO PUBLIC.BOOK_INFO (AUTHOR, AVAILABILITY, DESCRIPTION, NAME, PRICE) VALUES ('Ed Brubaker', true, 'Collects Captain America (2004) #22-24. Captain America has fallen into a clash with his government and his friends, and the people close to him are paying the price. The life of Cap''s girlfriend, Agent 13, is torn apart as her superiors use her divided loyalties against her. Elsewhere, a new villain emerges; the Red Skull begins to make himself known; and the Winter Soldier again comes face-to-face with Cap. But which side will he choose?', 'Civil War: Captain America', 29.2);
INSERT INTO PUBLIC.BOOK_INFO (AUTHOR, AVAILABILITY, DESCRIPTION, NAME, PRICE) VALUES ('George Beahm', true, 'The Stephen King Companion is an authoritative look at horror author King''s personal life and professional career, from Carrie to The Bazaar of Bad Dreams.

King expert George Beahm, who has published extensively about Maine''s main author, is your seasoned guide to the imaginative world of Stephen King, covering his varied and prodigious output: juvenalia, short fiction, limited edition books, bestselling novels, and film adaptations. The book is also profusely illustrated with nearly 200 photos, color illustrations by celebrated "Dark Tower" artist Michael Whelan, and black-and-white drawings by Maine artist Glenn Chadbourne.

Supplemented with interviews with friends, colleagues, and mentors who knew King well, this book looks at his formative years in Durham, when he began writing fiction as a young teen, his college years in the turbulent sixties, his struggles with early poverty, working full-time as an English teacher while writing part-time, the long road to the publication of his first novel, Carrie, and the dozens of bestselling books and major screen adaptations that followed.

For fans old and new, The Stephen King Companion is a comprehensive look at America''s best-loved bogeyman.', 'The Stephen King Companion: Four Decades of Fear from the Master of Horror', 54.1);
INSERT INTO PUBLIC.BOOK_INFO (AUTHOR, AVAILABILITY, DESCRIPTION, NAME, PRICE) VALUES ('George Beahm', true, 'After failing the test to gain entrance to the nation’s prestigious wizarding school, Yanko Whitefox worries he’ll never be able to regain his family’s lost honor.

Then war breaks out, his home is targeted, and survival becomes more of a concern than accolades.

With his family scattered, Yanko’s only allies are a politically incorrect parrot, a sarcastic sculptor, and a gifted warrior who may be an enemy spy.

Determined to stop the war and bring peace to his nation, Yanko must undertake a quest that will put his fledgling power to the ultimate test. If he fails, he’ll lose much more than honor.

For fans of underdogs, humor, and heroic fantasy, this bundle includes three prequel novellas and all four novels in the Chains of Honor series. ', 'Chains of Honor: The Complete Series (Books 1-4)', 51);
INSERT INTO PUBLIC.BOOK_INFO (AUTHOR, AVAILABILITY, DESCRIPTION, NAME, PRICE) VALUES ('David Remnick', true, 'Winner of the Pulitzer Prize
One of the Best Books of the Year: The New York Times

From the editor of The New Yorker: a riveting account of the collapse of the Soviet Union, which has become the standard book on the subject. Lenin’s Tomb combines the global vision of the best historical scholarship with the immediacy of eyewitness journalism. Remnick takes us through the tumultuous 75-year period of Communist rule leading up to the collapse and gives us the voices of those who lived through it, from democratic activists to Party members, from anti-Semites to Holocaust survivors, from Gorbachev to Yeltsin to Sakharov. An extraordinary history of an empire undone, Lenin’s Tomb stands as essential reading for our times. ', 'Lenin''s Tomb: The Last Days of the Soviet Empire (Pulitzer Prize Winner)', 86.9);
INSERT INTO PUBLIC.BOOK_INFO (AUTHOR, AVAILABILITY, DESCRIPTION, NAME, PRICE) VALUES ('John Green', true, 'The award-winning, genre-defining debut from John Green, the #1 bestselling author of The Anthropocene Reviewed and The Fault in Our Stars
Winner of the Michael L. Printz Award • A Los Angeles Times Book Prize Finalist • A New York Times Bestseller • A USA Today Bestseller • NPR’s Top Ten Best-Ever Teen Novels • TIME magazine’s 100 Best Young Adult Novels of All Time • A PBS Great American Read Selection • Millions of copies sold!

First drink. First prank. First friend. First love.

Last words.

Miles Halter is fascinated by famous last words—and tired of his safe life at home. He leaves for boarding school to seek what the dying poet François Rabelais called the “Great Perhaps.” Much awaits Miles at Culver Creek, including Alaska Young, who will pull Miles into her labyrinth and catapult him into the Great Perhaps.

Looking for Alaska brilliantly chronicles the indelible impact one life can have on another. A modern classic, this stunning debut marked #1 bestselling author John Green’s arrival as a groundbreaking new voice in contemporary fiction.

Newly updated edition includes a brand-new Readers'' Guide featuring a Q&A with author John Green', 'Looking for Alaska', 55.9);
INSERT INTO PUBLIC.BOOK_INFO (AUTHOR, AVAILABILITY, DESCRIPTION, NAME, PRICE) VALUES ('Barbara W. Tuchman', true, 'PULITZER PRIZE WINNER • “A brilliant piece of military history which proves up to the hilt the force of Winston Churchill’s statement that the first month of World War I was ‘a drama never surpassed.’”—Newsweek

Selected by the Modern Library as one of the 100 best nonfiction books of all time

In this landmark account, renowned historian Barbara W. Tuchman re-creates the first month of World War I: thirty days in the summer of 1914 that determined the course of the conflict, the century, and ultimately our present world. Beginning with the funeral of Edward VII, Tuchman traces each step that led to the inevitable clash. And inevitable it was, with all sides plotting their war for a generation. Dizzyingly comprehensive and spectacularly portrayed with her famous talent for evoking the characters of the war’s key players, Tuchman’s magnum opus is a classic for the ages.

The Proud Tower, the Pulitzer Prize–winning The Guns of August, and The Zimmermann Telegram comprise Barbara W. Tuchman’s classic histories of the First World War era', 'The Guns of August: The Outbreak of World War I; Barbara W. Tuchman''s Great War Series', 53.1);
INSERT INTO PUBLIC.BOOK_INFO (AUTHOR, AVAILABILITY, DESCRIPTION, NAME, PRICE) VALUES ('Phyllis Curott', false, 'Discover the magical power of Witchcraft and spellcasting, and manifest clarity, healing, and transformation for yourself, others, and the natural world.

We''re all seeking inner peace and ways to make meaningful change in our lives. But during troubled times, how can we find a way out of overwhelming stress and negativity? Allow leading Wiccan priestess Phyllis Curott to open the door to the realms of real, life-changing magic.

Spells for Living Well is an essential guide to the empowering magic of spells. Working with the elements, the natural world, and your own inner magic, Phyllis guides you through each spell with clear, vivid explanations. She helps you work at your own pace to discover your natural ability to tune in to the divine magic within and all around you. You''ll also learn how to craft your own spells.

This transformative spellbook invites you to manifest positive change in many of the daily issues affecting us in modern life—from climate change, to disconnection, to stress and anxiety. Phyllis weaves together her powerful Witchcraft wisdom and magic to teach you:

· positive energy spells to relieve anxiety and find calm within
· healing spells for living in peace and harmony
· banishing spells to break bad habits and cycles
· true love spells to open your heart and draw love in
· empowerment spells to create a life of joy, love, and grace

Magic and manifestation are waiting for you in Spells for Living Well. When you focus your mind and intention and take consistent action, you’ll be able to create a new, more connected, and empowered way to live.
', 'Spells for Living Well: A Witch''s Guide for Manifesting Change, Well-being, and Wonder', 63.7);
INSERT INTO PUBLIC.BOOK_INFO (AUTHOR, AVAILABILITY, DESCRIPTION, NAME, PRICE) VALUES ('Erin Hunter', false, 'Erin Hunter’s #1 bestselling Warriors series continues! Discover more thrilling adventures in this third book in the Starless Clan arc.
A crossroads for the Clans—and the warrior code!

The warrior code now allows a cat to formally change Clans, but ShadowClan’s newest arrival feels increasingly unwelcome—and though ShadowClan warrior Sunbeam thinks every cat deserves a chance, one of the loudest voices of opposition is her own mother. Meanwhile medicine cat apprentice Frostpaw scrambles to help RiverClan stay afloat under the watchful eye of a second Tigerstar, and the shadow of growing conflict looms over them all…

This seventh epic Warriors series is full of action, intrigue, and adventure—a perfect introduction for new readers and for long-time fans eager to discover what unfolds after the events of The Broken Code.', 'Warriors: A Starless Clan #3: Shadow', 75.8);

INSERT INTO PUBLIC.BOOK_COMMENT (BOOK_ID, CONTENT, CREATE_TIME, LAST_UPDATE_TIME, USER_ID) VALUES (1, 'Good book Civil War', '2024-04-08 01:19:08.856000', '2024-04-08 01:19:08.856000', 'admin1');
INSERT INTO PUBLIC.BOOK_COMMENT (BOOK_ID, CONTENT, CREATE_TIME, LAST_UPDATE_TIME, USER_ID) VALUES (2, 'Good book The Stephen King Companion', '2024-04-08 01:19:27.706000', '2024-04-08 01:19:27.706000', 'admin1');
INSERT INTO PUBLIC.BOOK_COMMENT (BOOK_ID, CONTENT, CREATE_TIME, LAST_UPDATE_TIME, USER_ID) VALUES (1, 'nice Civil War', '2024-04-08 01:19:50.490000', '2024-04-08 01:19:50.490000', 'user1');
INSERT INTO PUBLIC.BOOK_COMMENT (BOOK_ID, CONTENT, CREATE_TIME, LAST_UPDATE_TIME, USER_ID) VALUES (2, 'nice The Stephen King Companion', '2024-04-08 01:20:02.830000', '2024-04-08 01:20:02.830000', 'user1');
INSERT INTO PUBLIC.BOOK_COMMENT (BOOK_ID, CONTENT, CREATE_TIME, LAST_UPDATE_TIME, USER_ID) VALUES (1, 'great Civil War', '2024-04-08 01:20:25.860000', '2024-04-08 01:20:25.860000', 'user2');
INSERT INTO PUBLIC.BOOK_COMMENT (BOOK_ID, CONTENT, CREATE_TIME, LAST_UPDATE_TIME, USER_ID) VALUES (2, 'Great The Stephen King Companion', '2024-04-08 01:20:44.960000', '2024-04-08 01:20:44.960000', 'user2');
INSERT INTO PUBLIC.BOOK_COMMENT (BOOK_ID, CONTENT, CREATE_TIME, LAST_UPDATE_TIME, USER_ID) VALUES (1, 'Bad Civil War', '2024-04-08 01:21:26.622000', '2024-04-08 01:21:26.622000', 'admin2');
INSERT INTO PUBLIC.BOOK_COMMENT (BOOK_ID, CONTENT, CREATE_TIME, LAST_UPDATE_TIME, USER_ID) VALUES (2, 'Bad The Stephen King Companion', '2024-04-08 01:21:38.725000', '2024-04-08 01:21:38.725000', 'admin2');

INSERT INTO PUBLIC.USER_ORDERS (CREATE_TIME, DELIVERY_ADDR, EMAIL_ADDR, FULL_NAME, LAST_UPDATE_TIME, TOTAL_PRICE, USER_ID) VALUES ('2024-04-08 01:33:28.683000', 'RM 212, 232, KT', 'peter@hkmu.com', 'Peter Lee', '2024-04-08 01:33:28.683000', 585.1, 'user1');
INSERT INTO PUBLIC.USER_ORDERS (CREATE_TIME, DELIVERY_ADDR, EMAIL_ADDR, FULL_NAME, LAST_UPDATE_TIME, TOTAL_PRICE, USER_ID) VALUES ('2024-04-08 01:58:23.168000', 'Superluck Ind Centre, Tsuen Wan District, Hong Kong', 'apple@google.com', 'Apple Chan', '2024-04-08 01:58:23.168000', 137.9, 'user1');
INSERT INTO PUBLIC.USER_ORDERS (CREATE_TIME, DELIVERY_ADDR, EMAIL_ADDR, FULL_NAME, LAST_UPDATE_TIME, TOTAL_PRICE, USER_ID) VALUES ('2024-04-08 01:59:02.711000', 'O/S 154 Des Voeux Rd C, Sheung Wan, Hong Kong', 'keith@book.com', 'Keith Lee', '2024-04-08 01:59:02.711000', 53.1, 'admin2');

INSERT INTO PUBLIC.USER_ORDER_BOOK (ID, AUTHOR, DESCRIPTION, NAME, ORDER_ID, PRICE, QUANTITY) VALUES ('554d0dae-6761-4419-8117-5d22c69d88eb', 'Ed Brubaker', 'Collects Captain America (2004) #22-24. Captain America has fallen into a clash with his government and his friends, and the people close to him are paying the price. The life of Cap''s girlfriend, Agent 13, is torn apart as her superiors use her divided loyalties against her. Elsewhere, a new villain emerges; the Red Skull begins to make himself known; and the Winter Soldier again comes face-to-face with Cap. But which side will he choose?', 'Civil War: Captain America', 1, 29.2, 4);
INSERT INTO PUBLIC.USER_ORDER_BOOK (ID, AUTHOR, DESCRIPTION, NAME, ORDER_ID, PRICE, QUANTITY) VALUES ('5c13db24-7f88-4c17-93c8-98f8727faec9', 'George Beahm', 'The Stephen King Companion is an authoritative look at horror author King''s personal life and professional career, from Carrie to The Bazaar of Bad Dreams.

King expert George Beahm, who has published extensively about Maine''s main author, is your seasoned guide to the imaginative world of Stephen King, covering his varied and prodigious output: juvenalia, short fiction, limited edition books, bestselling novels, and film adaptations. The book is also profusely illustrated with nearly 200 photos, color illustrations by celebrated "Dark Tower" artist Michael Whelan, and black-and-white drawings by Maine artist Glenn Chadbourne.

Supplemented with interviews with friends, colleagues, and mentors who knew King well, this book looks at his formative years in Durham, when he began writing fiction as a young teen, his college years in the turbulent sixties, his struggles with early poverty, working full-time as an English teacher while writing part-time, the long road to the publication of his first novel, Carrie, and the dozens of bestselling books and major screen adaptations that followed.

For fans old and new, The Stephen King Companion is a comprehensive look at America''s best-loved bogeyman.', 'The Stephen King Companion: Four Decades of Fear from the Master of Horror', 1, 54.1, 3);
INSERT INTO PUBLIC.USER_ORDER_BOOK (ID, AUTHOR, DESCRIPTION, NAME, ORDER_ID, PRICE, QUANTITY) VALUES ('07ff1f1f-9478-4c1f-a182-8fc3c6cb5395', 'George Beahm', 'After failing the test to gain entrance to the nation’s prestigious wizarding school, Yanko Whitefox worries he’ll never be able to regain his family’s lost honor.

Then war breaks out, his home is targeted, and survival becomes more of a concern than accolades.

With his family scattered, Yanko’s only allies are a politically incorrect parrot, a sarcastic sculptor, and a gifted warrior who may be an enemy spy.

Determined to stop the war and bring peace to his nation, Yanko must undertake a quest that will put his fledgling power to the ultimate test. If he fails, he’ll lose much more than honor.

For fans of underdogs, humor, and heroic fantasy, this bundle includes three prequel novellas and all four novels in the Chains of Honor series. ', 'Chains of Honor: The Complete Series (Books 1-4)', 1, 51, 6);
INSERT INTO PUBLIC.USER_ORDER_BOOK (ID, AUTHOR, DESCRIPTION, NAME, ORDER_ID, PRICE, QUANTITY) VALUES ('87c0cb1c-aa49-4f7c-a64e-e388a827ceb5', 'George Beahm', 'After failing the test to gain entrance to the nation’s prestigious wizarding school, Yanko Whitefox worries he’ll never be able to regain his family’s lost honor.

Then war breaks out, his home is targeted, and survival becomes more of a concern than accolades.

With his family scattered, Yanko’s only allies are a politically incorrect parrot, a sarcastic sculptor, and a gifted warrior who may be an enemy spy.

Determined to stop the war and bring peace to his nation, Yanko must undertake a quest that will put his fledgling power to the ultimate test. If he fails, he’ll lose much more than honor.

For fans of underdogs, humor, and heroic fantasy, this bundle includes three prequel novellas and all four novels in the Chains of Honor series. ', 'Chains of Honor: The Complete Series (Books 1-4)', 2, 51, 1);
INSERT INTO PUBLIC.USER_ORDER_BOOK (ID, AUTHOR, DESCRIPTION, NAME, ORDER_ID, PRICE, QUANTITY) VALUES ('d76fe496-2bb2-45aa-9c8c-4e4801e5d929', 'David Remnick', 'Winner of the Pulitzer Prize
One of the Best Books of the Year: The New York Times

From the editor of The New Yorker: a riveting account of the collapse of the Soviet Union, which has become the standard book on the subject. Lenin’s Tomb combines the global vision of the best historical scholarship with the immediacy of eyewitness journalism. Remnick takes us through the tumultuous 75-year period of Communist rule leading up to the collapse and gives us the voices of those who lived through it, from democratic activists to Party members, from anti-Semites to Holocaust survivors, from Gorbachev to Yeltsin to Sakharov. An extraordinary history of an empire undone, Lenin’s Tomb stands as essential reading for our times. ', 'Lenin''s Tomb: The Last Days of the Soviet Empire (Pulitzer Prize Winner)', 2, 86.9, 1);
INSERT INTO PUBLIC.USER_ORDER_BOOK (ID, AUTHOR, DESCRIPTION, NAME, ORDER_ID, PRICE, QUANTITY) VALUES ('a78e799d-660b-4e32-b046-a41b349d45c7', 'Barbara W. Tuchman', 'PULITZER PRIZE WINNER • “A brilliant piece of military history which proves up to the hilt the force of Winston Churchill’s statement that the first month of World War I was ‘a drama never surpassed.’”—Newsweek

Selected by the Modern Library as one of the 100 best nonfiction books of all time

In this landmark account, renowned historian Barbara W. Tuchman re-creates the first month of World War I: thirty days in the summer of 1914 that determined the course of the conflict, the century, and ultimately our present world. Beginning with the funeral of Edward VII, Tuchman traces each step that led to the inevitable clash. And inevitable it was, with all sides plotting their war for a generation. Dizzyingly comprehensive and spectacularly portrayed with her famous talent for evoking the characters of the war’s key players, Tuchman’s magnum opus is a classic for the ages.

The Proud Tower, the Pulitzer Prize–winning The Guns of August, and The Zimmermann Telegram comprise Barbara W. Tuchman’s classic histories of the First World War era', 'The Guns of August: The Outbreak of World War I; Barbara W. Tuchman''s Great War Series', 3, 53.1, 1);
