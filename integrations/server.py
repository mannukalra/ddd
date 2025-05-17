from flask import Flask

app = Flask(__name__)
APPLICATION_ROOT = "/integ"

@app.route(f"{APPLICATION_ROOT}/offerings")
def get_offering():
    return ['Fiber 100', 'Fiber 200', 'Antina 300', 'Coax 400']

if __name__ == '__main__':
    app.run(debug=False, port=5050, host='0.0.0.0')
