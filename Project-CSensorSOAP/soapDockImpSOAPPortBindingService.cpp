/* soapDockImpSOAPPortBindingService.cpp
   Generated by gSOAP 2.8.41 for sensor.h

gSOAP XML Web services tools
Copyright (C) 2000-2017, Robert van Engelen, Genivia Inc. All Rights Reserved.
The soapcpp2 tool and its generated software are released under the GPL.
This program is released under the GPL with the additional exemption that
compiling, linking, and/or using OpenSSL is allowed.
--------------------------------------------------------------------------------
A commercial use license is available from Genivia Inc., contact@genivia.com
--------------------------------------------------------------------------------
*/

#include "soapDockImpSOAPPortBindingService.h"

DockImpSOAPPortBindingService::DockImpSOAPPortBindingService()
{	this->soap = soap_new();
	this->soap_own = true;
	DockImpSOAPPortBindingService_init(SOAP_IO_DEFAULT, SOAP_IO_DEFAULT);
}

DockImpSOAPPortBindingService::DockImpSOAPPortBindingService(const DockImpSOAPPortBindingService& rhs)
{	this->soap = rhs.soap;
	this->soap_own = false;
}

DockImpSOAPPortBindingService::DockImpSOAPPortBindingService(struct soap *_soap)
{	this->soap = _soap;
	this->soap_own = false;
	DockImpSOAPPortBindingService_init(_soap->imode, _soap->omode);
}

DockImpSOAPPortBindingService::DockImpSOAPPortBindingService(soap_mode iomode)
{	this->soap = soap_new();
	this->soap_own = true;
	DockImpSOAPPortBindingService_init(iomode, iomode);
}

DockImpSOAPPortBindingService::DockImpSOAPPortBindingService(soap_mode imode, soap_mode omode)
{	this->soap = soap_new();
	this->soap_own = true;
	DockImpSOAPPortBindingService_init(imode, omode);
}

DockImpSOAPPortBindingService::~DockImpSOAPPortBindingService()
{	if (this->soap_own)
		soap_free(this->soap);
}

void DockImpSOAPPortBindingService::DockImpSOAPPortBindingService_init(soap_mode imode, soap_mode omode)
{	soap_imode(this->soap, imode);
	soap_omode(this->soap, omode);
	static const struct Namespace namespaces[] = {
        {"SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/", "http://www.w3.org/*/soap-envelope", NULL},
        {"SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/", "http://www.w3.org/*/soap-encoding", NULL},
        {"xsi", "http://www.w3.org/2001/XMLSchema-instance", "http://www.w3.org/*/XMLSchema-instance", NULL},
        {"xsd", "http://www.w3.org/2001/XMLSchema", "http://www.w3.org/*/XMLSchema", NULL},
        {"ns1", "http://dockingsystem/", NULL, NULL},
        {NULL, NULL, NULL, NULL}
    };
	soap_set_namespaces(this->soap, namespaces);
}

void DockImpSOAPPortBindingService::destroy()
{	soap_destroy(this->soap);
	soap_end(this->soap);
}

void DockImpSOAPPortBindingService::reset()
{	this->destroy();
	soap_done(this->soap);
	soap_initialize(this->soap);
	DockImpSOAPPortBindingService_init(SOAP_IO_DEFAULT, SOAP_IO_DEFAULT);
}

#ifndef WITH_PURE_VIRTUAL
DockImpSOAPPortBindingService *DockImpSOAPPortBindingService::copy()
{	DockImpSOAPPortBindingService *dup = SOAP_NEW_COPY(DockImpSOAPPortBindingService);
	if (dup)
		soap_copy_context(dup->soap, this->soap);
	return dup;
}
#endif

DockImpSOAPPortBindingService& DockImpSOAPPortBindingService::operator=(const DockImpSOAPPortBindingService& rhs)
{	if (this->soap_own)
		soap_free(this->soap);
	this->soap = rhs.soap;
	this->soap_own = false;
	return *this;
}

int DockImpSOAPPortBindingService::soap_close_socket()
{	return soap_closesock(this->soap);
}

int DockImpSOAPPortBindingService::soap_force_close_socket()
{	return soap_force_closesock(this->soap);
}

int DockImpSOAPPortBindingService::soap_senderfault(const char *string, const char *detailXML)
{	return ::soap_sender_fault(this->soap, string, detailXML);
}

int DockImpSOAPPortBindingService::soap_senderfault(const char *subcodeQName, const char *string, const char *detailXML)
{	return ::soap_sender_fault_subcode(this->soap, subcodeQName, string, detailXML);
}

int DockImpSOAPPortBindingService::soap_receiverfault(const char *string, const char *detailXML)
{	return ::soap_receiver_fault(this->soap, string, detailXML);
}

int DockImpSOAPPortBindingService::soap_receiverfault(const char *subcodeQName, const char *string, const char *detailXML)
{	return ::soap_receiver_fault_subcode(this->soap, subcodeQName, string, detailXML);
}

void DockImpSOAPPortBindingService::soap_print_fault(FILE *fd)
{	::soap_print_fault(this->soap, fd);
}

#ifndef WITH_LEAN
#ifndef WITH_COMPAT
void DockImpSOAPPortBindingService::soap_stream_fault(std::ostream& os)
{	::soap_stream_fault(this->soap, os);
}
#endif

char *DockImpSOAPPortBindingService::soap_sprint_fault(char *buf, size_t len)
{	return ::soap_sprint_fault(this->soap, buf, len);
}
#endif

void DockImpSOAPPortBindingService::soap_noheader()
{	this->soap->header = NULL;
}

::SOAP_ENV__Header *DockImpSOAPPortBindingService::soap_header()
{	return this->soap->header;
}

#ifndef WITH_NOIO
int DockImpSOAPPortBindingService::run(int port)
{	if (!soap_valid_socket(this->soap->master) && !soap_valid_socket(this->bind(NULL, port, 100)))
		return this->soap->error;
	for (;;)
	{	if (!soap_valid_socket(this->accept()))
		{	if (this->soap->errnum == 0) // timeout?
				this->soap->error = SOAP_OK;
			break;
		}
		if (this->serve())
			break;
		this->destroy();
	}
	return this->soap->error;
}

#if defined(WITH_OPENSSL) || defined(WITH_GNUTLS)
int DockImpSOAPPortBindingService::ssl_run(int port)
{	if (!soap_valid_socket(this->soap->master) && !soap_valid_socket(this->bind(NULL, port, 100)))
		return this->soap->error;
	for (;;)
	{	if (!soap_valid_socket(this->accept()))
		{	if (this->soap->errnum == 0) // timeout?
				this->soap->error = SOAP_OK;
			break;
		}
		if (this->ssl_accept() || this->serve())
			break;
		this->destroy();
	}
	return this->soap->error;
}
#endif

SOAP_SOCKET DockImpSOAPPortBindingService::bind(const char *host, int port, int backlog)
{	return soap_bind(this->soap, host, port, backlog);
}

SOAP_SOCKET DockImpSOAPPortBindingService::accept()
{	return soap_accept(this->soap);
}

#if defined(WITH_OPENSSL) || defined(WITH_GNUTLS)
int DockImpSOAPPortBindingService::ssl_accept()
{	return soap_ssl_accept(this->soap);
}
#endif
#endif

int DockImpSOAPPortBindingService::serve()
{
#ifndef WITH_FASTCGI
	this->soap->keep_alive = this->soap->max_keep_alive + 1;
#endif
	do
	{
#ifndef WITH_FASTCGI
		if (this->soap->keep_alive > 0 && this->soap->max_keep_alive > 0)
			this->soap->keep_alive--;
#endif
		if (soap_begin_serve(this->soap))
		{	if (this->soap->error >= SOAP_STOP)
				continue;
			return this->soap->error;
		}
		if ((dispatch() || (this->soap->fserveloop && this->soap->fserveloop(this->soap))) && this->soap->error && this->soap->error < SOAP_STOP)
		{
#ifdef WITH_FASTCGI
			soap_send_fault(this->soap);
#else
			return soap_send_fault(this->soap);
#endif
		}
#ifdef WITH_FASTCGI
		soap_destroy(this->soap);
		soap_end(this->soap);
	} while (1);
#else
	} while (this->soap->keep_alive);
#endif
	return SOAP_OK;
}

static int serve_ns1__transferDataSOAP(struct soap*, DockImpSOAPPortBindingService*);
static int serve_ns1__handshakeSOAP(struct soap*, DockImpSOAPPortBindingService*);

int DockImpSOAPPortBindingService::dispatch()
{	return dispatch(this->soap);
}

int DockImpSOAPPortBindingService::dispatch(struct soap* soap)
{
	DockImpSOAPPortBindingService_init(soap->imode, soap->omode);

	soap_peek_element(soap);
	if (!soap_match_tag(soap, soap->tag, "ns1:transferDataSOAP"))
		return serve_ns1__transferDataSOAP(soap, this);
	if (!soap_match_tag(soap, soap->tag, "ns1:handshakeSOAP"))
		return serve_ns1__handshakeSOAP(soap, this);
	return soap->error = SOAP_NO_METHOD;
}

static int serve_ns1__transferDataSOAP(struct soap *soap, DockImpSOAPPortBindingService *service)
{	struct ns1__transferDataSOAP soap_tmp_ns1__transferDataSOAP;
	struct ns1__transferDataSOAPResponse _param_1;
	soap_default_ns1__transferDataSOAPResponse(soap, &_param_1);
	soap_default_ns1__transferDataSOAP(soap, &soap_tmp_ns1__transferDataSOAP);
	if (!soap_get_ns1__transferDataSOAP(soap, &soap_tmp_ns1__transferDataSOAP, "ns1:transferDataSOAP", NULL))
		return soap->error;
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap->error;
	soap->error = service->transferDataSOAP(soap_tmp_ns1__transferDataSOAP._arg0, soap_tmp_ns1__transferDataSOAP._arg1, soap_tmp_ns1__transferDataSOAP._arg2, _param_1);
	if (soap->error)
		return soap->error;
	soap->encodingStyle = NULL;
	soap_serializeheader(soap);
	soap_serialize_ns1__transferDataSOAPResponse(soap, &_param_1);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_ns1__transferDataSOAPResponse(soap, &_param_1, "ns1:transferDataSOAPResponse", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	};
	if (soap_end_count(soap)
	 || soap_response(soap, SOAP_OK)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_ns1__transferDataSOAPResponse(soap, &_param_1, "ns1:transferDataSOAPResponse", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap->error;
	return soap_closesock(soap);
}

static int serve_ns1__handshakeSOAP(struct soap *soap, DockImpSOAPPortBindingService *service)
{	struct ns1__handshakeSOAP soap_tmp_ns1__handshakeSOAP;
	struct ns1__handshakeSOAPResponse _param_1;
	soap_default_ns1__handshakeSOAPResponse(soap, &_param_1);
	soap_default_ns1__handshakeSOAP(soap, &soap_tmp_ns1__handshakeSOAP);
	if (!soap_get_ns1__handshakeSOAP(soap, &soap_tmp_ns1__handshakeSOAP, "ns1:handshakeSOAP", NULL))
		return soap->error;
	if (soap_body_end_in(soap)
	 || soap_envelope_end_in(soap)
	 || soap_end_recv(soap))
		return soap->error;
	soap->error = service->handshakeSOAP(_param_1);
	if (soap->error)
		return soap->error;
	soap->encodingStyle = NULL;
	soap_serializeheader(soap);
	soap_serialize_ns1__handshakeSOAPResponse(soap, &_param_1);
	if (soap_begin_count(soap))
		return soap->error;
	if (soap->mode & SOAP_IO_LENGTH)
	{	if (soap_envelope_begin_out(soap)
		 || soap_putheader(soap)
		 || soap_body_begin_out(soap)
		 || soap_put_ns1__handshakeSOAPResponse(soap, &_param_1, "ns1:handshakeSOAPResponse", "")
		 || soap_body_end_out(soap)
		 || soap_envelope_end_out(soap))
			 return soap->error;
	};
	if (soap_end_count(soap)
	 || soap_response(soap, SOAP_OK)
	 || soap_envelope_begin_out(soap)
	 || soap_putheader(soap)
	 || soap_body_begin_out(soap)
	 || soap_put_ns1__handshakeSOAPResponse(soap, &_param_1, "ns1:handshakeSOAPResponse", "")
	 || soap_body_end_out(soap)
	 || soap_envelope_end_out(soap)
	 || soap_end_send(soap))
		return soap->error;
	return soap_closesock(soap);
}
/* End of server object code */
