# User story: show the expected delivery date

As a customer, I want to see when my order will be delivered before I pay,
so that I know whether it arrives in time.

## Acceptance criteria

1. The expected delivery date is shown on the checkout page, e.g.
   "Delivered on Thursday 24 September".
2. Orders placed before 22:00 on a working day are shipped the same day.
   Orders placed later are shipped on the next working day.
3. Working days are Monday to Friday, except Dutch public holidays.
4. Parcels are delivered one day after shipping. Delivery takes place from
   Monday to Saturday; there is no delivery on Sundays and public holidays.
5. Large items (e.g. furniture) are delivered by a different carrier and
   take two extra working days.
6. If an item in the order is not in stock, "Delivery date unknown" is
   shown instead of a date.
7. Premium customers get their order delivered on Sundays too.
