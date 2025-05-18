from flask import Flask, request

app = Flask(__name__)
APPLICATION_ROOT = "/integ"

offerings_cache = [{'id': '100', 'name': 'Fiber 100', 'businessType': 'Internet'},
                   {'id': '200', 'name': 'Fiber 200', 'businessType': 'Internet'},
                   {'id': '300', 'name': 'Antina 300', 'businessType': 'Coax'},
                   {'id': '400', 'name': 'Coax 400', 'businessType': 'Coax'},
                   {'id': '500', 'name': 'Mobile 500', 'businessType': 'Mobile'}]


@app.route(f"{APPLICATION_ROOT}/offerings", methods=['GET', 'POST', 'DELETE'])
def offerings():
    if request.method == 'GET':
        return offerings_cache

    if request.method == 'POST':
        offerings_cache.extend(request.get_json())
        return offerings_cache
    elif request.method == 'DELETE':
        removal_item = request.get_json()
        for offering in offerings_cache:
            if offering['id'] == removal_item:
                offerings_cache.remove(offering)
        return offerings_cache

    return None

if __name__ == '__main__':
    app.run(debug=False, port=5050, host='0.0.0.0')
