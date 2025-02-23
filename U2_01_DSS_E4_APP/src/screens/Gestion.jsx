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

    const validacionUpdate = (correo, tel) => {
      let emailRegex = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/;
      let telRegex = /^[0-9]{10}$/;
      console.log("entro a validacionInput")
      console.log(emailRegex.test(correo))
      console.log(telRegex.test(tel))
      if(emailRegex.test(correo) && telRegex.test(tel)) {
        setEmailStatus(true);
        setTelStatus(true);
      }else {
        setEmailStatus(false);
        setTelStatus(false);
      }
    }
    
  
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

      if (!names || names.trim() === "") {
        Swal.fire("Advertencia", "Escribe el Nombre", "warning");
      } else if (!surname || surname.trim() === "") {
        Swal.fire("Advertencia", "Escribe el Apellido Paterno", "warning");
      } else if (!lastname || lastname.trim() === "") {
        Swal.fire("Advertencia", "Escribe el Apellido Materno", "warning");
      } else if (!email || email.trim() === "") {
        Swal.fire("Advertencia", "Escribe el correo", "warning");
      } else if (!emailStatus) {
        Swal.fire("Error", "El Correo Electrónico no es válido",'error');
      } else if (!phoneNumber || phoneNumber.trim() === "") {
        Swal.fire("Advertencia", "Escribe el número de telefono", "warning");
      }  else if (!telStatus) {
        Swal.fire("Error", "El Número Telefónico debe contener 10 digitos","error");
      }else {
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
        if (respuesta.status >= 200 && respuesta.status < 300) {
          getUsuarios();
          setModalOpen(false);
          setOpenEdit(false);
          limpiar();
        } else {
          // Maneja cualquier otro código de estado que no sea 2xx
          Swal.fire("Error", "Error en la Solicitud");
        }
      })
      .catch(function (error) {
        console.error(error); // Verifica el error en consola
        Swal.fire("Error", "Error en la Solicitud");
      });
    }

    const eliminarAlerta=(idUsuario)=>{
      event.preventDefault();
      Swal.fire({
        title:'Eliminar',
        text:'¿Eliminar usuario?',
        icon:'warning',
        cancelButtonText:'Cancelar',
        confirmButtonText:'Eliminar'
      }).then((r)=>{
      if(r.isConfirmed){
        eliminarUsuario(idUsuario)
      }}).then(() => {
        Swal.fire({
          title: 'Usuario eliminado',
          text: 'El usuario ha sido eliminado',
          icon: 'success',
        });
      }).catch((error) => {
          Swal.fire({
            title: 'Error',
            text: 'Hubo un problema al eliminar el usuario',
            icon: 'error',
          });
      });
    };

    const eliminarUsuario= async(idUsuario)=>{
      await axios({
        method: 'DELETE',
        url: url,
        data: {id:idUsuario},
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

    const limpiar=()=>{
      setEmailStatus(false);
      setTelStatus(false);
      setNames("");
      setSurname("");
      setLastname("");
      setEmail("");
      setPhoneNumber("");
  }

  useEffect(() => {
    if (modalEdit) {
      validacionUpdate(email, phoneNumber);
    }
  }, [modalEdit, email, phoneNumber]);

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
                                                    <button className="btn" style={{ backgroundColor: "#e75a5a", marginRight: "5px", width: '77px', maxWidth: '77px' }} onClick={() => eliminarAlerta(
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
                                <input type="email" className="form-control mb-2" name="email" placeholder="Correo" onChange={(e) => {setEmail(e.target.value); validarEmail(e.target.value);}}  required />
                                <input type="number" className="form-control mb-2" name="phoneNumber" placeholder="Teléfono" onChange={(e) => {setPhoneNumber(e.target.value);}}  required 
                                maxLength={10} onInput={ (e)=> {e.target.value= e.target.value.slice(0,10);
                                  if(e.target.value <0) e.target.value=""}
                                  }/>
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
                                <input type="email" className="form-control mb-2" name="email" placeholder="Correo"value={email} onChange={(e) => {setEmail(e.target.value);}} onInput={(e) => { validarEmail(e.target); }} required />
                                <input type="number" className="form-control mb-2" name="phoneNumber" placeholder="Teléfono" value={phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value);}} required  
                                maxLength={10} onInput={ (e)=> {e.target.value= e.target.value.slice(0,10);
                                  if(e.target.value <0) e.target.value=""}
                                  }/>
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

