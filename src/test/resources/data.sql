-- CUSTOMER
insert into customer (id, customer_type, first_name, last_name,  vatnumber, siren, company_name)
values ('5e18367a-1eb3-4b91-b87a-44cd210ef7ba', 'NATURAL', 'dimitar', 'angelov', null, null, null);
insert into customer ( id, customer_type, first_name, last_name, vatnumber, siren, company_name)
values ('aebb21fa-b981-4baa-9668-52be5ea3ce90', 'NATURAL', 'john', 'doe', null, null, null);
insert into customer ( id, customer_type, first_name, last_name, vatnumber, siren, company_name)
values ('95a5f70b-989d-4101-9f0c-6702d18fe9be', 'NATURAL', 'martin', 'leroy', null, null, null);
insert into customer ( id, customer_type, first_name, last_name, vatnumber, siren, company_name)
values ( 'eb3-b87a-44cd210ef7ba-4b91-5e18367a', 'NATURAL', 'jane', 'doe', null, null, null);
insert into customer ( id, customer_type, first_name, last_name, vatnumber, siren, company_name)
values ('939c0a28-c407-4ce3-b661-d96a412a3d29', 'LEGAL', null, null, '111111111', '00000000000001', 'Capco LTD');
insert into customer ( id, customer_type, first_name, last_name, vatnumber, siren, company_name)
values ('5e18367a-1eb3-44cd210ef7ba-4b91-b87a','LEGAL', null, null, '222222222', '00000000000002', 'MyCompany2');
insert into customer ( id, customer_type, first_name, last_name, vatnumber, siren, company_name)
values ('1e8b5bca-ba37-4a8c-83df-e57074343119', 'LEGAL', null, null, '333333333', '00000000000003', 'MyCompany3');
insert into customer ( id, customer_type, first_name, last_name, vatnumber, siren, company_name)
values ('e37a04a6-273e-4a7f-8715-01cc9b0661a3', 'LEGAL', null, null, '444444444', '00000000000004', 'MyCompany4');

-- PRODUCT
insert into product (id,  product_type, name,description)
values ('47a79602-86b9-4925-99a2-e180dbc1512c','SMARTPHONE_PREMIUM','IPhone15','Most recent IPhone model');
insert into product (id,  product_type, name,description)
values ('08d0a16f-ab32-46a0-bf33-0c03f5da3f5c','SMARTPHONE_PREMIUM','IPhone14','Premium IPhone model');
insert into product (id,  product_type, name,description)
values ('4d97c198-97b6-4a62-9731-70882c9dce31','SMARTPHONE_STANDARD','Samsung Galaxy','Middle range Samsung Galaxy model');
insert into product (id,  product_type, name,description)
values ('da197df6-8195-4d60-9865-36497716eb91','LAPTOP','Apple MacBook','Apple MacBook Pro 2025');

-- PRICE
insert into product_price (id, product_type, price,currency_code)
values ('46a9b7f0-4ed0-4b6a-acce-8a81913eaa07','SMARTPHONE_PREMIUM', 1500,'EUR' );
insert into product_price (id, product_type,  price,currency_code)
values ('860a552a-3db7-439f-b6c5-fef205cf7906','SMARTPHONE_STANDARD', 800,'EUR' );
insert into product_price (id, product_type,  price,currency_code)
values ('45d29a6d-4ce0-46a5-9fa9-c1bfd0db1b85','LAPTOP', 800,'EUR' );