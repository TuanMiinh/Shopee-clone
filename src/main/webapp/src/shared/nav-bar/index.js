import React from "react";
import "./style.scss";
import { Navbar, Nav, Container } from "react-bootstrap";
import {
  FaShoppingCart,
  FaFacebookMessenger,
  FaInstagram,
} from "react-icons/fa";
import logo from "../../assets/logo.svg";
import { Link } from "react-router-dom";

export default function NavBar() {
  return (
    <div className="header">
      <Container>
        <Navbar expand="lg">
          {/* <Navbar.Brand href="#home">
          <img src={logo} alt="logo" className="logo" />
        </Navbar.Brand> */}
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="#home">Supplier Channel</Nav.Link>
              <Nav.Link href="#link">Download</Nav.Link>
              <Nav.Link href="#link">
                Connect <FaFacebookMessenger /> <FaInstagram />
              </Nav.Link>
            </Nav>
          </Navbar.Collapse>
          <Navbar.Collapse className="justify-content-end">
            <Link to="/signup">Sign up</Link>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/login">Login</Link>
          </Navbar.Collapse>
        </Navbar>

        <div className="under-nav">
          <div className="logo">
            <img src={logo} alt="logo" />
          </div>
          <div className="search-bar">
            <input type="text" placeholder="Search product" />
          </div>
          <div className="cart-icon">
            <FaShoppingCart size={36} />
          </div>
        </div>
      </Container>
    </div>
  );
}
