import React from "react";
import "./style.scss";
import { Row, Col, Container } from "react-bootstrap";
import DetailProduct from "../detail/index";

export default function ListProducts(props) {
  const { products } = props;
  console.log(products);
  return (
    <Container>
      <Row xs={1} md={4} className="g-1 mr-0 ml-0 mt-2">
        {[...products].map((item, index) => {
          return (
            <Col className="mt-3">
              <DetailProduct item={item} key={index}></DetailProduct>
            </Col>
          );
        })}
      </Row>
    </Container>
  );
}
