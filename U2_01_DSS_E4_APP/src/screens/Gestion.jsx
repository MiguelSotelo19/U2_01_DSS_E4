import React, {use, useEffect, useState} from "react";
import axios from "axios";
import Modal from "react-modal";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
//id usuario operacion fecha
//operaciones GET/ PUT / POST / DELETE

const customStyles = {
    content: {
      width: "auto",
      height: "auto",
      top: "50%",
      left: "50%",
      right: "auto",
      bottom: "auto",
      marginRight: "-50%",
      transform: "translate(-50%, -50%)",
      borderWidth: 2,
      borderColor: "black",
      display: "flex",
      flexDirection: "column",
      alignItems: "center",
      justifyContent: "center"
    },
};
  
Modal.setAppElement("#root");

export const Gestion = () => { 
    const url = "";
    const [ usuarios, setUsuarios ] = useState([
        {
            id: 1,
            nombre: 'Juan',
            apellidoPaterno: 'Pérez',
            apellidoMaterno: 'González',
            correo: 'juan.perez@email.com',
            numeroTelefonico: '555-1234',
            edad: 30
          },
          {
            id: 2,
            nombre: 'Ana',
            apellidoPaterno: 'López',
            apellidoMaterno: 'Martínez',
            correo: 'ana.lopez@email.com',
            numeroTelefonico: '555-5678',
            edad: 25
          }
    ]);
    const [ emailStatus, setEmailStatus ] = useState(false);
    const [ telStatus, setTelStatus ] = useState(false);

    const [ id, setId ] = useState(0);
    const [ nombre, setNombre ] = useState("");
    const [ apellidoPaterno, setApe_m ] = useState("");
    const [ apellidoMaterno, setApe_p ] = useState("");
    const [ correo, setCorreo ] = useState("");
    const [ numeroTelefoncico, setNumT ] = useState("");
    const [ edad, setEdad ] = useState("");

    const [ modalEdit, setOpenEdit ] = React.useState(false);

    function closeModalEdit() { setOpenEdit(false); }

    const openModalEdit = async (id, nombre, apellidoPaterno, apellidoMaterno, correo, numeroTelefoncico, edad) => {
        setOpenEdit(true);

        setId(id);
        setIdPersonas(idPersonas);
        setNombre(nombre);
        setApe_m(apellidoPaterno);
        setApe_p(apellidoMaterno);
        setCorreo(correo);
        setNumT(numeroTelefoncico);
        setEdad(edad);
    }


    function afterOpenModal() {
        subtitle.style.color = "#f00";
    }


    /*useEffect( () => {
        getUsuarios();
    }, []);*/

    const getUsuarios = async () => {
        const respuesta = await axios({
            method: "GET",          
            url: url 
        });
        setUsuarios(respuesta.data.data);
    }

    const validarEmail = (e) => {
        let campo = e;
            
        let emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        
        if (emailRegex.test(campo.value)) {        
          setEmailStatus(true);
        } else {
          setEmailStatus(false);
        }
      }
  
      const validarTel = (e) => {
        let campo = e;
            
        let telRegex = /^[0-9]{10}$/;
        
        if (telRegex.test(campo.value)) {  
          setTelStatus(true);
        } else {
          setTelStatus(false);
        }
      }

    let usuario = localStorage.getItem("usuario")
    if(usuario != 'Iniciado') {
        window.location = '/E403';
    }

    const cerrarSesion = () => {
        localStorage.setItem("usuario", "No Iniciado");
        window.location = '/';
    }

    return(
        <>
        <div className="main">
            <button onClick={() => cerrarSesion()}>Salir</button>
            <div className="container-fluid">
                <div className="row mt-3">
                    <div className="col-12 col-lg-8 offset-0 offset-lg-2">
                        <div className="table-responsive">
                            <table className="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Nombre</th>
                                        <th>Apellido Paterno</th>
                                        <th>Apellido Materno</th>
                                        <th>Correo</th>
                                        <th>Teléfono</th>
                                        <th>Edad</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody className="table-group-divider">
                                    {Array.isArray(usuarios) && usuarios.length > 0 ? (
                                    usuarios.map((user, i) => (
                                        <tr key={user.id}>
                                            <td>{i+1}</td>
                                            <td>{user.nombre}</td>
                                            <td>{user.apellidoPaterno}</td>
                                            <td>{user.apellidoMaterno}</td>
                                            <td>{user.correo}</td>
                                            <td>{user.numeroTelefonico}</td>
                                            <td>{user.edad}</td>
                                            <td>
                                                <div className="d-flex align-items-center">
                                                <button className="btn btn-warning mr-2" style={{ width: '77px', maxWidth: '77px'}} onClick={() => openModalEdit(
                                                        user.id, user.nombre, user.apellidoPaterno, user.apellidoMaterno, user.correo, user.numeroTelefonico
                                                    )}>Editar</button>
                                                    <button className="btn" style={{ backgroundColor: "#e75a5a", marginRight: "5px", width: '77px', maxWidth: '77px' }} onClick={() => deleteUsuario(
                                                        usuario.id, usuario.nombre)}>Eliminar</button>
                                                </div>
                                            </td>
                                        </tr>
                                    ))
                                    ) : (
                                        <tr>
                                            <th>No hay datos</th>
                                        </tr>                                        
                                    )
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <Modal
              isOpen={modalEdit}
              onAfterOpen={afterOpenModal}
              onRequestClose={closeModalEdit}
              style={customStyles}
              contentLabel="Modal Edit"
          >
            <h2 style={{color: "#e67e22", fontSize: 35}}>Editar Usuario</h2>
            <form style={{
                width: "90%",
                display: "flex", 
                flexDirection: "column", 
                alignItems: "center"}}>

              <div className="info-1">
                <div className="field">
                  <span className="labInp">Nombre(s)</span>
                  <input required type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} />
                </div>

                <div className="field">
                  <span className="labInp">Apellido Paterno</span>
                  <input required type="text" placeholder="Apellido Paterno" value={apellidoPaterno} onChange={(e) => setApe_p(e.target.value)} />
                </div>
              </div>

              <div className="info-1">
                <div className="field">
                  <span className="labInp">Apellido Materno</span>
                  <input required type="text" placeholder="Apellido Materno" value={apellidoMaterno} onChange={(e) => setApe_m(e.target.value)} />
                </div>

                <div className="field">
                  <span className="labInp">Núm. de Teléfono</span>
                  <input required type="number" maxLength={10} minLength={10} min={0} placeholder="Número de Teléfono" onInput={
                  (e) => { validarTel(e.target); } }
                   value={numeroTelefoncico} onChange={(e) => setNumeroTelefoncico(e.target.value)} />
                </div>
              </div>

              <div className="info-1">
                <div className="field">
                  <span className="labInp">Correo Electrónico</span>
                  <input required type="email" onInput={
                    (e) => { validarEmail(e.target); }
                  } placeholder="Correo Electrónico" value={correo} onChange={(e) => setCorreo(e.target.value)} />
                </div>

                <div className="field">
                  <span className="labInp">Edad</span>
                  <input required type="number" maxLength={10} minLength={10} min={0} placeholder="Edad"
                   value={edad} onChange={(e) => setEdad(e.target.value)} />
                </div>
              </div>
              
              

            <div className="acciones">
              <button id="recu" onClick={() => validar("PUT")}>Guardar Cambios</button>
              <button className="cancelar" id="cancelarEdit" onClick={closeModalEdit}>Cancelar</button>
            </div>
            
            </form>
          </Modal>

        </>
    );
}

