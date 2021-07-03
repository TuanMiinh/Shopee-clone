import React, { useEffect, useState } from "react";
import "./style.scss";
import { Form, Button, Container } from "react-bootstrap";
import axios from "axios";
import login from "../../assets/login.svg";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect,
} from "react-router-dom";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleOnChangeUsername = (e) => {
    setUsername(e.target.value);
  };

  const handleOnChangePassword = (e) => {
    setPassword(e.target.value);
  };

  const handleLogin = (event) => {
    event.preventDefault();
    console.log(username, password);

    axios
      .post(
        "ShopeeClone/users",
        {
          username,
          password,
        },
        {
          headers: {
            "access-control-allow-origin": "*",
            "Content-type": "application/json; charset=UTF-8",
          },
        }
      )
      .then(({ data }) => {
        if (data) {
          
        }
      });
  };
  return (
    <>
      <div className="body">
        <div>
          <Form onSubmit={handleLogin}>
            <div className="img-container">
              <img src={login} alt="login" className="img-login" />
            </div>
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Username</Form.Label>
              <Form.Control
                type="text"
                value={username}
                name="username"
                onChange={handleOnChangeUsername}
                placeholder="Enter username"
              />
            </Form.Group>

            <Form.Group controlId="formBasicPassword" className="mt-3">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                value={password}
                name="password"
                onChange={handleOnChangePassword}
                placeholder="Password"
              />
            </Form.Group>
            <Form.Text className="text-muted">
              Don't have an account? <Link to="/signup">Sign up</Link>.
            </Form.Text>
            {/* <Form.Group controlId="formBasicCheckbox">
              <Form.Check type="checkbox" label="Check me out" />
            </Form.Group> */}
            <Button className="btn-submit mt-3" type="submit">
              Login
            </Button>
          </Form>
        </div>
      </div>
    </>
  );
}
