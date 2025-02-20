import { useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import { redirect, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export const Login = () => { 

    const [ usuario, setUsuario ] = useState("");
    const [ contra, setContra ] = useState("");

    const navigate = useNavigate();

    let usuarios=[
        {"usuario":"Diego","contraseña":"1234"},
        {"usuario":"Miguel","contraseña":"1234"}
    ]

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
                localStorage.setItem("usuario", JSON.stringify(usuarioIniciado));
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
    return(
        <>
        <Container>
        <Form>
            <Form.Label>Nombre usuario</Form.Label>
            <Form.Control placeholder="Nombre usuario" onChange={(e) => setUsuario(e.target.value)}/>
            <Form.Label > Contraseña</Form.Label>
            <Form.Control placeholder="Contraseña" type="password" onChange={(e) => setContra(e.target.value)}/>
        </Form>
        <Button className="form-submit-button" type="submit" onClick={() => iniciarSesion()}>
                Iniciar sesión
              </Button>
        </Container>
        
        </>
    );
}