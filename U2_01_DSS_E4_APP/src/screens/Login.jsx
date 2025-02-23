import axios from "axios";
import { useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import { redirect, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

import './css/Login.css';

export const Login = () => { 
    const urlAuth = "http://127.0.0.1:8080/api/auth/signin";

    const [ usuario, setUsuario ] = useState("");
    const [ contra, setContra ] = useState("");

    const navigate = useNavigate();

    const validar = async () => {
  
      try {
          const res = await axios.post(urlAuth, {
              username: usuario,
              password: contra
          });
          
          localStorage.setItem("token", res.data.data);
          navigate("/Gestion");
      } catch (error) {
          console.error("Error en la petición:", error);
      }
  };
  

    return (
        <>
          <Container className="login-container">
            <div className="login-form">
              <Form.Label className="form-label">Nombre usuario</Form.Label>
              <Form.Control 
                className="form-control" 
                placeholder="Usuario" 
                onChange={(e) => setUsuario(e.target.value)} 
              />
              
              <Form.Label className="form-label">Contraseña</Form.Label>
              <Form.Control 
                className="form-control" 
                placeholder="Contraseña" 
                type="password" 
                onChange={(e) => setContra(e.target.value)} 
              />
              
              <Button 
                className="form-submit-button"
                onClick={() => validar()}
              >
                Iniciar sesión
              </Button>
            </div>
          </Container>
        </>
      );
}