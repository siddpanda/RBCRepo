# RBCRepo
RBCRepo

Approach:
GET /stock/{ticker} to get stock info which returns multiple stocks. 404 if no stock returned
POST /stock - takes a list of stocks as json and returns 201 creeated or 500 if the transaction fails
Uses CSV file as the datasource


2nd Iteration :
introduce traceid for tracing api call and transactions for troubleshoot
authentication


Launch :
http://localhost:8081/DowjonesService/ to get the application swagger.


Search a stock with ticker
curl -X GET "http://localhost:8081/DowjonesService/stock/aa" -H "accept: */*"


Add stock/upload bulk stock info
curl -X POST "http://localhost:8081/DowjonesService/stock" -H "accept: */*" -H "Content-Type: application/json" -d "[ { \"quarter\": \"1\", \"stock\": \"REL\", \"date\": \"1/7/2011\", \"open\": \"$147.21\", \"high\": \"$148.86\", \"low\": \"$146.64\", \"close\": \"$147.93\", \"volume\": \"23492843\", \"percent_change_price\": \"0.489097\", \"percent_change_volume_over_last_wk\": \"\", \"previous_weeks_volume\": \"\", \"next_weeks_open\": \"$147.00\", \"next_weeks_close\": \"$150.00\", \"percent_change_next_weeks_price\": \"2.04082\", \"days_to_next_dividend\": \"32\", \"percent_return_next_dividend\": \"0.439397\" }, { \"quarter\": \"1\", \"stock\": \"REL\", \"date\": \"1/7/2011\", \"open\": \"$147.21\", \"high\": \"$148.86\", \"low\": \"$146.64\", \"close\": \"$147.93\", \"volume\": \"23492843\", \"percent_change_price\": \"0.489097\", \"percent_change_volume_over_last_wk\": \"\", \"previous_weeks_volume\": \"\", \"next_weeks_open\": \"$147.00\", \"next_weeks_close\": \"$150.00\", \"percent_change_next_weeks_price\": \"2.04082\", \"days_to_next_dividend\": \"32\", \"percent_return_next_dividend\": \"0.439397\" }]"


Sample json input for testing bulk in swagger

[
  {
    "quarter": "1",
    "stock": "REL",
    "date": "1/7/2011",
    "open": "$147.21",
    "high": "$148.86",
    "low": "$146.64",
    "close": "$147.93",
    "volume": "23492843",
    "percent_change_price": "0.489097",
    "percent_change_volume_over_last_wk": "",
    "previous_weeks_volume": "",
    "next_weeks_open": "$147.00",
    "next_weeks_close": "$150.00",
    "percent_change_next_weeks_price": "2.04082",
    "days_to_next_dividend": "32",
    "percent_return_next_dividend": "0.439397"
  },
  {
    "quarter": "1",
    "stock": "REL",
    "date": "1/7/2011",
    "open": "$147.21",
    "high": "$148.86",
    "low": "$146.64",
    "close": "$147.93",
    "volume": "23492843",
    "percent_change_price": "0.489097",
    "percent_change_volume_over_last_wk": "",
    "previous_weeks_volume": "",
    "next_weeks_open": "$147.00",
    "next_weeks_close": "$150.00",
    "percent_change_next_weeks_price": "2.04082",
    "days_to_next_dividend": "32",
    "percent_return_next_dividend": "0.439397"
  }
]
