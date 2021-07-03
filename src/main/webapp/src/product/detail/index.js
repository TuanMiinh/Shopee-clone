import React from "react";
import "./style.scss";
import { FaRegHeart, FaCartPlus } from "react-icons/fa";

import { Card } from "react-bootstrap";

export default function DetailProduct(props) {
  const img = "https://placeimg.com/640/480/people";
  const { name: productName, price, discount } = props.item;

  const truncateName = (name) => {
    return name.length > 16 ? name.slice(0, 16) + "..." : name;
  };

  const formatPrice = (price) => {
    const priceString = price + "";
    let result = "";
    let count = 0;

    for (let i = priceString.length - 1; i >= 0; i--) {
      count++;
      if (count === 3) {
        result =
          i !== 0 ? "." + priceString[i] + result : priceString[i] + result;
        count = 0;
      } else {
        result = priceString[i] + result;
      }
    }

    return result;
  };

  const convertPrice = (price) => {
    price = price + "";

    return price.slice(0, price.length - 3) + "000";
  };

  const formatNumber = (number) => {
    number = number + "";
    return number.length === 1 ? "0" + number : number;
  }
  return (
    <Card className="h-100">
      <div className="sale">
        {formatNumber(discount)}%
      </div>
      <Card.Img variant="top" src={img} />
      <Card.Body>
        <Card.Title className="title">{truncateName(productName)}</Card.Title>
        <Card.Text className="text">
          <span className="discount-price">
            {formatPrice(
              convertPrice(Math.ceil((price * 100) / (100 - discount)))
            )}
          </span>{" "}
          - đ{formatPrice(price)}
        </Card.Text>
        <Card.Text className="info">
          <div className="heart">
            <FaRegHeart size={16} />
          </div>
          <Card.Text className="add-to-cart">
            <FaCartPlus /> Add To Cart
          </Card.Text>
        </Card.Text>
      </Card.Body>
    </Card>
  );
}
