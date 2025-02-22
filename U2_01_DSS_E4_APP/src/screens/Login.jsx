import { useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import { redirect, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export const Login = () => { 
    const urlUser = "http://localhost:8080/cliente/";
    const [ usuarios_, setUsuarios ] = useState([]);
    const [ usuario, setUsuario ] = useState("");
    const [ contra, setContra ] = useState("");

    const navigate = useNavigate();

    let usuarios=[
        {"usuario":"Diego","contraseña":"1234"},
        {"usuario":"Miguel","contraseña":"1234"}
    ]

    const getUsuarios = async () => {
        const respuesta = await axios({
            method: "GET",          
            url: url 
        });
        setUsuarios(respuesta.data.data);
    }

    function iniciarSesion(){
        let usuarioIniciado= null;
        usuarioIniciado={
            "usuario": usuario,
            "contraseña": contra
        }
        let si=0;
        for (let i=0; i<usuarios.length;i++){
            let user= usuarios[i];
            if(usuarioIniciado.usuario==user.usuario && usuarioIniciado.contraseña==user.contraseña){
                localStorage.setItem("usuario", "Iniciado");
                navigate("/Gestion");
                si=1;
                break;
            } else{
                si=0;
            }
        }
        if(si==0){
            Swal.fire("Usuario no encontrado","Uusario y/o contraseña erroneos","error");
        }
    }

    return (
        <>
          <Container className="login-container">
            <Form className="login-form">
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
                type="submit" 
                onClick={() => iniciarSesion()}
              >
                Iniciar sesión
              </Button>
            </Form>
          </Container>
    
          <style jsx>{`
            body {
              background-color: #f4f7fa;
              font-family: 'Arial', sans-serif;
              margin: 0;
              display: flex;
              justify-content: center;
              align-items: center;
              height: 100vh;
              padding: 0;
            }
    
            .login-container {
              background-color: #fff;
              border-radius: 8px;
              box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
              padding: 40px;
              width: 100%;
              max-width: 400px;
            }
    
            .login-form {
              display: flex;
              flex-direction: column;
              gap: 20px;
            }
    
            .form-label {
              font-size: 16px;
              color: #333;
            }
    
            .form-control {
              font-size: 14px;
              padding: 10px;
              border: 1px solid #ccc;
              border-radius: 4px;
            }
    
            .form-control:focus {
              border-color: #e67e22;
              box-shadow: 0 0 0 0.2rem rgba(230, 126, 34, 0.25);
            }
    
            .form-submit-button {
              background-color: #e67e22;
              color: #fff;
              padding: 10px;
              font-size: 16px;
              border: none;
              border-radius: 4px;
              cursor: pointer;
            }
    
            .form-submit-button:hover {
              background-color: #d35400;
            }
    
            .form-submit-button:active {
              background-color: #c0392b;
            }
          `}</style>
        </>
      );
}