import Swal from "sweetalert2";
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
    const url = "http://127.0.0.1:8080/api/almacen/person/";
    const [ usuarios, setUsuarios ] = useState([]);
    const [ emailStatus, setEmailStatus ] = useState(false);
    const [ telStatus, setTelStatus ] = useState(false);

    const [ id, setId ] = useState(0);
    const [ names, setNames ] = useState("");
    const [ surname, setSurname ] = useState("");
    const [ lastname, setLastname ] = useState("");
    const [ email, setEmail ] = useState("");
    const [ phoneNumber, setPhoneNumber ] = useState("");


    const [modalOpen, setModalOpen] = useState(false);
    const [ modalEdit, setOpenEdit ] = React.useState(false);

    let token = localStorage.getItem("token")
    if(token == "null" || token == null) {
        window.location = '/E403';
    }

    //console.log(token)
    const cerrarSesion = () => {
        localStorage.setItem("token", null);
        window.location = '/';
    }

    function closeModalEdit() { setOpenEdit(false); }

    const openModalEdit = async (id, names, surname, lastname, email, phoneNumer) => {
      console.log(phoneNumber);
        setId(id);
        setNames(names);
        setSurname(surname);
        setLastname(lastname);
        setEmail(email);
        setPhoneNumber(phoneNumer);

        setOpenEdit(true);
    }


    useEffect( () => {
        getUsuarios();
    }, []);

    const getUsuarios = async () => {
        const respuesta = await axios({
            method: "GET",          
            url: url,
            headers: {
              Authorization: `Bearer ${token}`
            }   
        });
        console.log(respuesta.data.data)
        setUsuarios(respuesta.data.data);
    }

    const validarEmail = (e) => {
      let email = typeof e === "string" ? e : e?.value; 
    
      let emailRegex = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/;
    
      if (emailRegex.test(email)) {        
        setEmailStatus(true);
      } else {
        setEmailStatus(false);
      }
    };
    
  
    const validarTel = (e) => {
      let telefono = typeof e === "string" ? e : e?.value;
    
      let telRegex = /^[0-9]{10}$/;
    
      if (telRegex.test(telefono)) {  
        setTelStatus(true);
      } else {
        setTelStatus(false);
      }
    };
    


    const validar = (metodo) => {
      event.preventDefault();

      validarEmail(email);
      validarTel(phoneNumber);

      if (names.trim() === "") {
        Swal.fire("Advertencia", "Escribe el Nombre", "warning");
      } else if (surname.trim() === "") {
        Swal.fire("Advertencia", "Escribe el Apellido Paterno", "warning");
      } else if (lastname.trim() === "") {
        Swal.fire("Advertencia", "Escribe el Apellido Materno", "warning");
      } else if (email.trim() === "") {
        Swal.fire("Advertencia", "Escribe el correo", "warning");
      } else {
        const parametros = {
          id: id,
          names: names,
          surname: surname,
          lastname: lastname,
          birthdate: "1998-01-20",
          email: email,
          phoneNumber: parseInt(phoneNumber, 10) || 0,
          address: "",
          curp: ""
        };

        if (emailStatus && telStatus) {
          enviarSolicitud(metodo, parametros, url);
          getUsuarios();
        } else {
          if (!emailStatus) {
            Swal.fire("Error", "El Correo Electrónico no es válido");
          } else if (!telStatus) {
            Swal.fire("Error", "El Número Telefónico no es válido");
          }
        }
      }
    };

    const enviarSolicitud = async(metodo, parametros, url) => {
      event.preventDefault();

      await axios({
        method: metodo,
        url: url,
        data: parametros,
        headers: {
          Authorization: `Bearer ${token}`
        } 
      }).then(function (respuesta) {
        getUsuarios();
        setModalOpen(false);
        setOpenEdit(false);
      })
      .catch(function (error) {
        Swal.fire("Error", "Error en la Solicitud");
      });
    }


    return(
        <>
        <div className="main">
            <div className="d-flex justify-content-evenly">
              <button className="btn btn-primary" onClick={() => cerrarSesion()}>Salir</button>
              <button className="btn btn-success" onClick={() => {setModalOpen(true); setId(0);}}>Añadir</button>
            </div>
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
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody className="table-group-divider">
                                    {Array.isArray(usuarios) && usuarios.length > 0 ? (
                                    usuarios.map((user, i) => (
                                        <tr key={user.id}>
                                            <td>{i+1}</td>
                                            <td>{user.names}</td>
                                            <td>{user.surname}</td>
                                            <td>{user.lastname}</td>
                                            <td>{user.email}</td>
                                            <td>{user.phoneNumber}</td>
                                            <td>
                                                <div className="d-flex align-items-center">
                                                <button className="btn btn-warning mr-2" style={{ width: '77px', maxWidth: '77px'}} onClick={() => openModalEdit(
                                                        user.id, user.names, user.surname, user.lastname, user.email, user.phoneNumber
                                                    )}>Editar</button>
                                                    <button className="btn" style={{ backgroundColor: "#e75a5a", marginRight: "5px", width: '77px', maxWidth: '77px' }} onClick={() => deleteUsuario(
                                                        user.id)}>Eliminar</button>
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


        {modalOpen && (
            <div className="modal fade show d-block" tabIndex="-1">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Añadir Usuario</h5>
                            <button type="button" className="btn-close" onClick={() => setModalOpen(false)}></button>
                        </div>
                        <div className="modal-body">
                            <form>
                                <input type="text" className="form-control mb-2" name="names" placeholder="Nombre" onChange={(e) => setNames(e.target.value)}  required />
                                <input type="text" className="form-control mb-2" name="surname" placeholder="Apellido Paterno" onChange={(e) => setSurname(e.target.value)}  required />
                                <input type="text" className="form-control mb-2" name="lastname" placeholder="Apellido Materno" onChange={(e) => setLastname(e.target.value)}  required />
                                <input type="email" className="form-control mb-2" name="email" placeholder="Correo" onChange={(e) => {setEmail(e.target.value);}}  required />
                                <input type="text" className="form-control mb-2" name="phoneNumber" placeholder="Teléfono" onChange={(e) => {setPhoneNumber(e.target.value);}}  required />
                                <button className="btn btn-primary" onClick={() => validar("POST")}>Guardar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )}

        {modalEdit && (
            <div className="modal fade show d-block" tabIndex="-1">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Actualizar Usuario</h5>
                            <button type="button" className="btn-close" onClick={closeModalEdit}></button>
                        </div>
                        <div className="modal-body">
                            <form>
                                <input type="text" className="form-control mb-2" name="names" placeholder="Nombre" value={names} onChange={(e) => setNames(e.target.value)} required/>
                                <input type="text" className="form-control mb-2" name="surname" placeholder="Apellido Paterno" value={surname} onChange={(e) => setSurname(e.target.value)} required />
                                <input type="text" className="form-control mb-2" name="lastname" placeholder="Apellido Materno" value={lastname} onChange={(e) => setLastname(e.target.value)} required />
                                <input type="email" className="form-control mb-2" name="email" placeholder="Correo"value={email} onChange={(e) => {setEmail(e.target.value);}} required />
                                <input type="text" className="form-control mb-2" name="phoneNumber" placeholder="Teléfono" value={phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value);}} required  />
                                <button className="btn btn-primary" onClick={() => validar("PUT")}>
                                    Actualizar
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )}

        </>
    );
}

