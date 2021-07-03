import React, { useState } from "react";
import "./style.scss";
import axios from "axios";
import { Formik, Field, Form } from "formik";
import register from "../../assets/register.svg";

export default function SignUp() {
  return (
    <>
      <div className="body">
        <div>
          <Formik
            initialValues={{
              name: "",
              username: "",
              password: "",
              email: "",
              phoneNumber: "",
            }}
            onSubmit={(values) => {
              axios.post("ShopeeClone/user", values).then(({ data }) => {
                console.log(data);
              })
            }}
          >
            <Form>
              <div className="img-container">
                <img src={register} alt="login" className="img-login" />
              </div>
              <label className="form-label">Name</label>
              <Field name="name" type="text" className="form-control" />
              <label className="form-label">Username</label>
              <Field name="username" type="text" className="form-control" />
              <label className="form-label">Password</label>
              <Field name="password" type="password" className="form-control" />
              <label className="form-label">Email</label>
              <Field name="email" type="email" className="form-control" />
              <label className="form-label">Phone number</label>
              <Field name="phoneNumber" type="text" className="form-control" />
              <br></br>
              <button className="btn btn-primary" type="submit">
                Submit
              </button>
            </Form>
          </Formik>
        </div>
      </div>
    </>
  );
}
