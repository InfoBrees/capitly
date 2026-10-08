# IDEE
- was brauchen wir von der externen crypto api und was wollen wir überhaupt darstellen? 
--- live updates der coins in bestimmten zeitabständen (5min - 15min) -> A
--- generelle coin informationen (anfangskurse, schlusskurse, marktkapitalisierung) -> B
--- vergangenheit der einzelnen coins -> C

# API
- get_live (A) -> WEBSOCKET
- get_coin (B) -> COIN
- get_coin_history (C)-> COIN

- add_coin -> WATCHLIST
- remove_coin -> WATCHLIST

# TODO
- JWT angeben 
- für jeden endpunkt nötigen scopes/clearances definieren um bestimmen zu erreichen
