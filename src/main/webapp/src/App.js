import "./App.scss";
import Home from "./home";
import NavBar from "./shared/nav-bar";
import "bootstrap/dist/css/bootstrap.min.css";
import "./custom.scss";

import Login from "./shared/login/index";
import SignUp from "./shared/sign-up";

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect,
} from "react-router-dom";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <link
          rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous"
        />
        <link rel="preconnect" href="https://fonts.gstatic.com" />
        <link
          href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap"
          rel="stylesheet"
        />
      </header>
      <body>
        <div className="App">
          <div>
            <Switch>
              <Route path="/home">
                <NavBar></NavBar>
                <Home />
              </Route>
              <Route path="/login">
                <Login />
              </Route>
              <Route path="/signup">
                <SignUp />
              </Route>
              <Route path="/">
                <Redirect to="/home" />
              </Route>
            </Switch>
          </div>
        </div>
      </body>
    </div>
  );  
}

export default App;
