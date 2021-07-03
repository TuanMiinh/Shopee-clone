import React from "react";
import "./style.scss";
import Phone from "./styles/images/phone.png";
import Mail from "./styles/images/mail.png";
import User from "./styles/images/user.svg";
import Logo from "./styles/images/logo.svg";
import Search from "./styles/images/search.png";
import Heart from "./styles/images/heart.png";
import Cart from "./styles/images/cart.png";
import Laptop from "./styles/images/shopping_cart.jpg";
import Remove from "./styles/images/remove.png";
import Delivery from "./styles/images/delivery.png";
import Pay from "./styles/images/pay.png";
import Ship from "./styles/images/ship.png";
import AppStore from "./styles/images/AppStore.png";
import GGplay from "./styles/images/GGplay.png";
import AppGallery from "./styles/images/appGallery.png";
import { FaPlusCircle } from "react-icons/fa";
import { FaMinusCircle } from "react-icons/fa";
import { FaFacebookSquare } from "react-icons/fa";
import { FaInstagramSquare } from "react-icons/fa";
import { FaLinkedin } from "react-icons/fa";



export default function Cartpage() {
    return(
        <>
            <div className="super">
  {/* =========== HEADER =========== */}
  <div className="header">
    {/* Top header */}
    <div className="top-bar">
      <div className="center-1">
        <div className="phone-number">
          <img src={Phone} alt="" />
          <span>+38 068 005 3570</span>
        </div>
        <div className="gmail">
          <img src={Mail} alt="" />
          <a href="#"> <span>fastsales@gmail.com</span> </a>
        </div>
        <div className="top-bar-content">
          <div className="country-money">
            <div className="country-money-list">
              <div><span>English</span></div>
              <div><span>$ Dollar</span></div>
            </div>
          </div>
          <div className="form">
            <div className="icon-form">
              <img src={User} alt="" />
            </div>
            <div className="register">
              <a href="#">Register</a>
            </div>
            <div>
              <a href="#">Log-in</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    {/* Main header */}
    <div className="main-header">
      <div className="center-2">
        {/* Logo */}
        <div className="logo">
          <img src={Logo} alt="" />
        </div>
        {/* Search */}
        <div className="header-search">
            <input type="text" placeholder="Search for products" />
            <button type="submit" value="submit">
                <img src={Search} alt="search" />
            </button>
        </div>
        {/* Wishlist */}
        <div className="wishlist">
          {/* Wishlist-icon */}
          <div className="wishlist-icon">
            <img src={Heart} alt="heart" />
            <div className="wishlist-text">
              <a href="#">Wishlist</a>
            </div>
          </div>
          {/* cart-icon */}
          <div className="cart-icon">
            <div className="cart-icon-img">
              <img src={Cart} alt="cart" />
              <div className="cart-count"><span>1</span></div>
            </div>
            <div className="cart-text">
              <a href="#">Wishlist</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    {/* Main Navigation */}
    <div className="main-nav">
      <div className="center-3">
        <div className="main-nav-content">
          {/* Categories Menu */}
          <div className="cat_menu_container">
            <div className="cat_menu_text">
              categories
            </div>
          </div>
          {/* Main Nav Menu */}
          <div className="main_nav_menu">
            <ul>
              <li><a href="#">Home</a></li>
              <li><a href="#">Super Deals</a></li>
              <li><a href="#">Fetured Brands</a></li>
              <li><a href="#">Pages</a></li>
              <li><a href="#">Blog</a></li>
              <li><a href="#">Contact</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  {/* =========== CART ============= */}
  <div className="cart-section">
    <div className="center-4">
      <div className="balance-row">
        <div className="mini-balance-row">
          <div className="cart-container">
            <div className="cart-title">Shopping Cart</div>
            {/* Cart items */}
            <div className="cart-items">
              <div className="cart-list">
                <div className="cart-item">
                  <div className="cart-item-img">
                    <img src={Laptop} alt="" />
                  </div>
                  <div className="cart-item-info">
                    <div className="cart-item-name">
                      <div className="cart-item-title">Name</div>
                      <div className="cart-item-text">MacBook Air 13</div>
                    </div>
                    <div className="cart-item-quantity">
                      <div className="cart-item-title">Quantity</div>
                      <div className="cart-item-text">
                        <div className="icon-chevron"><FaMinusCircle/></div>
                        <input type="text" className="input" defaultValue={1} />
                        <div className="icon-chevron"><FaPlusCircle/></div>
                      </div>
                    </div>
                    <div className="cart-item-price">
                      <div className="cart-item-title">Price</div>
                      <div className="cart-item-text">$2000</div>
                    </div>
                    <div className="cart-item-total">
                      <div className="cart-item-title">Total</div>
                      <div className="cart-item-text">$2000</div>
                    </div>
                    <div className="cart-item-delete">
                      <img src={Remove} alt="" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {/* Order Total */}
            <div className="order-total">
              <div className="order-total-content">
                <div className="order-total-address">
                  <img src={Delivery} alt="" /><span>Delivery address:</span>
                  <input type="text" placeholder="Your addredd..." />
                </div>
                <div className="order_total_title">Order Total:</div>
                <div className="order_total_amount">$2000</div>
              </div>
            </div>
            {/* Cart-buttons */}
            <div className="cart-buttons">
              <button type="button" className="button cart_button_clear">Back</button>
              <button type="button" className="button cart_button_checkout">Delivery Now</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  {/* =============== Footer =============== */}
  <div className="footer">
    <div className="f-container">
      <div className="item-container">
        <div className="title">customer care</div>
        <ul className="info-item">
          <li><a href>Help Center</a></li>
          <li><a href>Shopee Blog</a></li>
          <li><a href>Shopee Mall</a></li>
          <li><a href>Shopping guide</a></li>
          <li><a href>Pay</a></li>
          <li><a href>Shopee Coins</a></li>
          <li><a href>Transport</a></li>
          <li><a href>Returns &amp; Refunds</a></li>
          <li><a href>Customer care</a></li>
          <li><a href>Warranty Policy</a></li>
        </ul>
      </div>
      <div className="item-container">
        <div className="title">ABOUT SHOPEE</div>
        <ul className="info-item">
          <li><a href>About Shopee</a></li>
          <li><a href>Recruitment</a></li>
          <li><a href>Shopee Terms</a></li>
          <li><a href>Privacy Policy</a></li>
          <li><a href>Genuine</a></li>
          <li><a href>Kênh Người Bán</a></li>
          <li><a href>Flash Sales</a></li>
          <li><a href>Shopee Affiliate Program</a></li>
          <li><a href>Contact Media</a></li>
        </ul>
      </div>
      <div className="item-container">
        <div className="title">PAY</div>
        <img src={Pay} alt="Pay" />
        <div className="title">TRANSPORTATION UNIT</div>
        <img src={Ship} alt="Ship" />
      </div>
      <div className="item-container">
        <div className="title">FOLLOW US</div>
        <ul className="list-icon">
          <li><FaFacebookSquare/><a href>Facebook</a></li>
          <li><FaInstagramSquare/><a href>Instagram</a></li>
          <li><FaLinkedin/><a href>Linkedln</a></li>
        </ul>
      </div>
      <div className="item-container">
        <div className="title">DOWNLOAD SHOPEE APP NOW</div>
        <div className="downl-app">
          <a href><img src={AppStore} alt="" /></a>
          <a href><img src={GGplay} alt="" /></a>
          <a href><imge src={AppGallery} alt="" /></a>
        </div>
      </div>
    </div>
    <div className="l-footer">
      <div className="first">
        © 2021 Shopee. All rights reserved.
      </div>
      <div className="second">
        <div className="area">Country &amp; Region:</div>
        <div className="f-country">Singapore</div>
        <div className="country">Indonesia</div>
        <div className="country">Taiwan</div>
        <div className="country">Thailand</div>
        <div className="country">Malaysia</div>
        <div className="country">Vietnam</div>
        <div className="country">Philippines</div>
        <div className="country">Brazil</div>
        <div className="country">Mexico</div>
        <div className="country">Colombia</div>
        <div className="country">Chile</div>
      </div>
    </div>
  </div>
</div>

        </>
    );
}