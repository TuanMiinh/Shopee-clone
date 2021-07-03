import React, { useEffect, useState } from "react";
import "./style.scss";
import ListProducts from "../product/list";
import axios from "axios";

export default function Home() {
  const [products, setProducts] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);

  useEffect(() => {
    axios
      .get("ShopeeClone/products?page=1", {
        headers: {
          "access-control-allow-origin": "*",
          "Content-type": "application/json; charset=UTF-8",
        },
      })
      .then((result) => {
        setProducts(result.data);
        setIsLoaded(true);
      });
  }, []);

  return <>{isLoaded && <ListProducts products={products}></ListProducts>}</>;
}
