

/* this ALWAYS GENERATED file contains the definitions for the interfaces */


 /* File created by MIDL compiler version 8.xx.xxxx */
/* at a redacted point in time
 */
/* Compiler settings for ../../edge_embedded_browser/client/win/current/webview2experimental.idl:
    Oicf, W1, Zp8, env=Win64 (32b run), target_arch=AMD64 8.xx.xxxx 
    protocol : dce , ms_ext, c_ext, robust
    error checks: allocation ref bounds_check enum stub_data 
    VC __declspec() decoration level: 
         __declspec(uuid()), __declspec(selectany), __declspec(novtable)
         DECLSPEC_UUID(), MIDL_INTERFACE()
*/
/* @@MIDL_FILE_HEADING(  ) */

#pragma warning( disable: 4049 )  /* more than 64k source lines */


/* verify that the <rpcndr.h> version is high enough to compile this file*/
#ifndef __REQUIRED_RPCNDR_H_VERSION__
#define __REQUIRED_RPCNDR_H_VERSION__ 475
#endif

#include "rpc.h"
#include "rpcndr.h"

#ifndef __RPCNDR_H_VERSION__
#error this stub requires an updated version of <rpcndr.h>
#endif /* __RPCNDR_H_VERSION__ */


#ifndef __webview2experimental_h__
#define __webview2experimental_h__

#if defined(_MSC_VER) && (_MSC_VER >= 1020)
#pragma once
#endif

/* Forward Declarations */ 

#ifndef __ICoreWebView2ExperimentalCompositionController_FWD_DEFINED__
#define __ICoreWebView2ExperimentalCompositionController_FWD_DEFINED__
typedef interface ICoreWebView2ExperimentalCompositionController ICoreWebView2ExperimentalCompositionController;

#endif 	/* __ICoreWebView2ExperimentalCompositionController_FWD_DEFINED__ */


#ifndef __ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_FWD_DEFINED__
#define __ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_FWD_DEFINED__
typedef interface ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler;

#endif 	/* __ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_FWD_DEFINED__ */


#ifndef __ICoreWebView2ExperimentalPointerInfo_FWD_DEFINED__
#define __ICoreWebView2ExperimentalPointerInfo_FWD_DEFINED__
typedef interface ICoreWebView2ExperimentalPointerInfo ICoreWebView2ExperimentalPointerInfo;

#endif 	/* __ICoreWebView2ExperimentalPointerInfo_FWD_DEFINED__ */


#ifndef __ICoreWebView2ExperimentalCursorChangedEventHandler_FWD_DEFINED__
#define __ICoreWebView2ExperimentalCursorChangedEventHandler_FWD_DEFINED__
typedef interface ICoreWebView2ExperimentalCursorChangedEventHandler ICoreWebView2ExperimentalCursorChangedEventHandler;

#endif 	/* __ICoreWebView2ExperimentalCursorChangedEventHandler_FWD_DEFINED__ */


#ifndef __ICoreWebView2ExperimentalEnvironment_FWD_DEFINED__
#define __ICoreWebView2ExperimentalEnvironment_FWD_DEFINED__
typedef interface ICoreWebView2ExperimentalEnvironment ICoreWebView2ExperimentalEnvironment;

#endif 	/* __ICoreWebView2ExperimentalEnvironment_FWD_DEFINED__ */


/* header files for imported files */
#include "webview2.h"

#ifdef __cplusplus
extern "C"{
#endif 



#ifndef __WebView2Experimental_LIBRARY_DEFINED__
#define __WebView2Experimental_LIBRARY_DEFINED__

/* library WebView2Experimental */
/* [version][uuid] */ 





typedef /* [v1_enum] */ 
enum COREWEBVIEW2_MOUSE_EVENT_KIND
    {
        COREWEBVIEW2_MOUSE_EVENT_KIND_HORIZONTAL_WHEEL	= 0x20e,
        COREWEBVIEW2_MOUSE_EVENT_KIND_LEFT_BUTTON_DOUBLE_CLICK	= 0x203,
        COREWEBVIEW2_MOUSE_EVENT_KIND_LEFT_BUTTON_DOWN	= 0x201,
        COREWEBVIEW2_MOUSE_EVENT_KIND_LEFT_BUTTON_UP	= 0x202,
        COREWEBVIEW2_MOUSE_EVENT_KIND_LEAVE	= 0x2a3,
        COREWEBVIEW2_MOUSE_EVENT_KIND_MIDDLE_BUTTON_DOUBLE_CLICK	= 0x209,
        COREWEBVIEW2_MOUSE_EVENT_KIND_MIDDLE_BUTTON_DOWN	= 0x207,
        COREWEBVIEW2_MOUSE_EVENT_KIND_MIDDLE_BUTTON_UP	= 0x208,
        COREWEBVIEW2_MOUSE_EVENT_KIND_MOVE	= 0x200,
        COREWEBVIEW2_MOUSE_EVENT_KIND_RIGHT_BUTTON_DOUBLE_CLICK	= 0x206,
        COREWEBVIEW2_MOUSE_EVENT_KIND_RIGHT_BUTTON_DOWN	= 0x204,
        COREWEBVIEW2_MOUSE_EVENT_KIND_RIGHT_BUTTON_UP	= 0x205,
        COREWEBVIEW2_MOUSE_EVENT_KIND_WHEEL	= 0x20a,
        COREWEBVIEW2_MOUSE_EVENT_KIND_X_BUTTON_DOUBLE_CLICK	= 0x20d,
        COREWEBVIEW2_MOUSE_EVENT_KIND_X_BUTTON_DOWN	= 0x20b,
        COREWEBVIEW2_MOUSE_EVENT_KIND_X_BUTTON_UP	= 0x20c
    } 	COREWEBVIEW2_MOUSE_EVENT_KIND;

typedef /* [v1_enum] */ 
enum COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS
    {
        COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS_NONE	= 0,
        COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS_LEFT_BUTTON	= 0x1,
        COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS_RIGHT_BUTTON	= 0x2,
        COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS_SHIFT	= 0x4,
        COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS_CONTROL	= 0x8,
        COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS_MIDDLE_BUTTON	= 0x10,
        COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS_X_BUTTON1	= 0x20,
        COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS_X_BUTTON2	= 0x40
    } 	COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS;

DEFINE_ENUM_FLAG_OPERATORS(COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS);
typedef struct COREWEBVIEW2_MATRIX_4X4
    {
    FLOAT _11;
    FLOAT _12;
    FLOAT _13;
    FLOAT _14;
    FLOAT _21;
    FLOAT _22;
    FLOAT _23;
    FLOAT _24;
    FLOAT _31;
    FLOAT _32;
    FLOAT _33;
    FLOAT _34;
    FLOAT _41;
    FLOAT _42;
    FLOAT _43;
    FLOAT _44;
    } 	COREWEBVIEW2_MATRIX_4X4;

typedef /* [v1_enum] */ 
enum COREWEBVIEW2_POINTER_EVENT_KIND
    {
        COREWEBVIEW2_POINTER_EVENT_KIND_ACTIVATE	= 0x24b,
        COREWEBVIEW2_POINTER_EVENT_KIND_DOWN	= 0x246,
        COREWEBVIEW2_POINTER_EVENT_KIND_ENTER	= 0x249,
        COREWEBVIEW2_POINTER_EVENT_KIND_LEAVE	= 0x24a,
        COREWEBVIEW2_POINTER_EVENT_KIND_UP	= 0x247,
        COREWEBVIEW2_POINTER_EVENT_KIND_UPDATE	= 0x245
    } 	COREWEBVIEW2_POINTER_EVENT_KIND;


EXTERN_C const IID LIBID_WebView2Experimental;

#ifndef __ICoreWebView2ExperimentalCompositionController_INTERFACE_DEFINED__
#define __ICoreWebView2ExperimentalCompositionController_INTERFACE_DEFINED__

/* interface ICoreWebView2ExperimentalCompositionController */
/* [unique][object][uuid] */ 


EXTERN_C const IID IID_ICoreWebView2ExperimentalCompositionController;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("19A479A7-4D44-4796-9D95-3B22269EED7D")
    ICoreWebView2ExperimentalCompositionController : public IUnknown
    {
    public:
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_UIAProvider( 
            /* [retval][out] */ IUnknown **provider) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_RootVisualTarget( 
            /* [retval][out] */ IUnknown **target) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_RootVisualTarget( 
            /* [in] */ IUnknown *target) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SendMouseInput( 
            /* [in] */ COREWEBVIEW2_MOUSE_EVENT_KIND eventKind,
            /* [in] */ COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS virtualKeys,
            /* [in] */ UINT32 mouseData,
            /* [in] */ POINT point) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE CreateCoreWebView2PointerInfoFromPointerId( 
            /* [in] */ UINT pointerId,
            /* [in] */ HWND parentWindow,
            /* [in] */ struct COREWEBVIEW2_MATRIX_4X4 transform,
            /* [retval][out] */ ICoreWebView2ExperimentalPointerInfo **pointerInfo) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE SendPointerInput( 
            /* [in] */ COREWEBVIEW2_POINTER_EVENT_KIND eventType,
            /* [in] */ ICoreWebView2ExperimentalPointerInfo *pointerInfo) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_Cursor( 
            /* [retval][out] */ HCURSOR *cursor) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE add_CursorChanged( 
            /* [in] */ ICoreWebView2ExperimentalCursorChangedEventHandler *eventHandler,
            /* [out] */ EventRegistrationToken *token) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE remove_CursorChanged( 
            /* [in] */ EventRegistrationToken token) = 0;
        
    };
    
    
#else 	/* C style interface */

    typedef struct ICoreWebView2ExperimentalCompositionControllerVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [in] */ REFIID riid,
            /* [annotation][iid_is][out] */ 
            _COM_Outptr_  void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            ICoreWebView2ExperimentalCompositionController * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            ICoreWebView2ExperimentalCompositionController * This);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_UIAProvider )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [retval][out] */ IUnknown **provider);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_RootVisualTarget )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [retval][out] */ IUnknown **target);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_RootVisualTarget )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [in] */ IUnknown *target);
        
        HRESULT ( STDMETHODCALLTYPE *SendMouseInput )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [in] */ COREWEBVIEW2_MOUSE_EVENT_KIND eventKind,
            /* [in] */ COREWEBVIEW2_MOUSE_EVENT_VIRTUAL_KEYS virtualKeys,
            /* [in] */ UINT32 mouseData,
            /* [in] */ POINT point);
        
        HRESULT ( STDMETHODCALLTYPE *CreateCoreWebView2PointerInfoFromPointerId )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [in] */ UINT pointerId,
            /* [in] */ HWND parentWindow,
            /* [in] */ struct COREWEBVIEW2_MATRIX_4X4 transform,
            /* [retval][out] */ ICoreWebView2ExperimentalPointerInfo **pointerInfo);
        
        HRESULT ( STDMETHODCALLTYPE *SendPointerInput )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [in] */ COREWEBVIEW2_POINTER_EVENT_KIND eventType,
            /* [in] */ ICoreWebView2ExperimentalPointerInfo *pointerInfo);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_Cursor )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [retval][out] */ HCURSOR *cursor);
        
        HRESULT ( STDMETHODCALLTYPE *add_CursorChanged )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [in] */ ICoreWebView2ExperimentalCursorChangedEventHandler *eventHandler,
            /* [out] */ EventRegistrationToken *token);
        
        HRESULT ( STDMETHODCALLTYPE *remove_CursorChanged )( 
            ICoreWebView2ExperimentalCompositionController * This,
            /* [in] */ EventRegistrationToken token);
        
        END_INTERFACE
    } ICoreWebView2ExperimentalCompositionControllerVtbl;

    interface ICoreWebView2ExperimentalCompositionController
    {
        CONST_VTBL struct ICoreWebView2ExperimentalCompositionControllerVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define ICoreWebView2ExperimentalCompositionController_QueryInterface(This,riid,ppvObject)	\
    ( (This)->lpVtbl -> QueryInterface(This,riid,ppvObject) ) 

#define ICoreWebView2ExperimentalCompositionController_AddRef(This)	\
    ( (This)->lpVtbl -> AddRef(This) ) 

#define ICoreWebView2ExperimentalCompositionController_Release(This)	\
    ( (This)->lpVtbl -> Release(This) ) 


#define ICoreWebView2ExperimentalCompositionController_get_UIAProvider(This,provider)	\
    ( (This)->lpVtbl -> get_UIAProvider(This,provider) ) 

#define ICoreWebView2ExperimentalCompositionController_get_RootVisualTarget(This,target)	\
    ( (This)->lpVtbl -> get_RootVisualTarget(This,target) ) 

#define ICoreWebView2ExperimentalCompositionController_put_RootVisualTarget(This,target)	\
    ( (This)->lpVtbl -> put_RootVisualTarget(This,target) ) 

#define ICoreWebView2ExperimentalCompositionController_SendMouseInput(This,eventKind,virtualKeys,mouseData,point)	\
    ( (This)->lpVtbl -> SendMouseInput(This,eventKind,virtualKeys,mouseData,point) ) 

#define ICoreWebView2ExperimentalCompositionController_CreateCoreWebView2PointerInfoFromPointerId(This,pointerId,parentWindow,transform,pointerInfo)	\
    ( (This)->lpVtbl -> CreateCoreWebView2PointerInfoFromPointerId(This,pointerId,parentWindow,transform,pointerInfo) ) 

#define ICoreWebView2ExperimentalCompositionController_SendPointerInput(This,eventType,pointerInfo)	\
    ( (This)->lpVtbl -> SendPointerInput(This,eventType,pointerInfo) ) 

#define ICoreWebView2ExperimentalCompositionController_get_Cursor(This,cursor)	\
    ( (This)->lpVtbl -> get_Cursor(This,cursor) ) 

#define ICoreWebView2ExperimentalCompositionController_add_CursorChanged(This,eventHandler,token)	\
    ( (This)->lpVtbl -> add_CursorChanged(This,eventHandler,token) ) 

#define ICoreWebView2ExperimentalCompositionController_remove_CursorChanged(This,token)	\
    ( (This)->lpVtbl -> remove_CursorChanged(This,token) ) 

#endif /* COBJMACROS */


#endif 	/* C style interface */




#endif 	/* __ICoreWebView2ExperimentalCompositionController_INTERFACE_DEFINED__ */


#ifndef __ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_INTERFACE_DEFINED__
#define __ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_INTERFACE_DEFINED__

/* interface ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler */
/* [unique][object][uuid] */ 


EXTERN_C const IID IID_ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("F8089361-D9C5-4A3C-B7FD-3633E28FFF39")
    ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler : public IUnknown
    {
    public:
        virtual HRESULT STDMETHODCALLTYPE Invoke( 
            HRESULT result,
            ICoreWebView2ExperimentalCompositionController *webView) = 0;
        
    };
    
    
#else 	/* C style interface */

    typedef struct ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandlerVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler * This,
            /* [in] */ REFIID riid,
            /* [annotation][iid_is][out] */ 
            _COM_Outptr_  void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler * This);
        
        HRESULT ( STDMETHODCALLTYPE *Invoke )( 
            ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler * This,
            HRESULT result,
            ICoreWebView2ExperimentalCompositionController *webView);
        
        END_INTERFACE
    } ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandlerVtbl;

    interface ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler
    {
        CONST_VTBL struct ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandlerVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_QueryInterface(This,riid,ppvObject)	\
    ( (This)->lpVtbl -> QueryInterface(This,riid,ppvObject) ) 

#define ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_AddRef(This)	\
    ( (This)->lpVtbl -> AddRef(This) ) 

#define ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_Release(This)	\
    ( (This)->lpVtbl -> Release(This) ) 


#define ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_Invoke(This,result,webView)	\
    ( (This)->lpVtbl -> Invoke(This,result,webView) ) 

#endif /* COBJMACROS */


#endif 	/* C style interface */




#endif 	/* __ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler_INTERFACE_DEFINED__ */


#ifndef __ICoreWebView2ExperimentalPointerInfo_INTERFACE_DEFINED__
#define __ICoreWebView2ExperimentalPointerInfo_INTERFACE_DEFINED__

/* interface ICoreWebView2ExperimentalPointerInfo */
/* [unique][object][uuid] */ 


EXTERN_C const IID IID_ICoreWebView2ExperimentalPointerInfo;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("909778FB-535F-47E8-BE0A-A02FF6AD27F0")
    ICoreWebView2ExperimentalPointerInfo : public IUnknown
    {
    public:
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PointerKind( 
            /* [retval][out] */ DWORD *pointerKind) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PointerKind( 
            /* [in] */ DWORD pointerKind) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PointerId( 
            /* [retval][out] */ UINT32 *pointerId) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PointerId( 
            /* [in] */ UINT32 pointerId) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_FrameId( 
            /* [retval][out] */ UINT32 *frameId) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_FrameId( 
            /* [in] */ UINT32 frameId) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PointerFlags( 
            /* [retval][out] */ UINT32 *pointerFlags) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PointerFlags( 
            /* [in] */ UINT32 pointerFlags) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PointerDeviceRect( 
            /* [retval][out] */ RECT *pointerDeviceRect) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PointerDeviceRect( 
            /* [in] */ RECT pointerDeviceRect) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_DisplayRect( 
            /* [retval][out] */ RECT *displayRect) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_DisplayRect( 
            /* [in] */ RECT displayRect) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PixelLocation( 
            /* [retval][out] */ POINT *pixelLocation) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PixelLocation( 
            /* [in] */ POINT pixelLocation) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_HimetricLocation( 
            /* [retval][out] */ POINT *himetricLocation) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_HimetricLocation( 
            /* [in] */ POINT himetricLocation) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PixelLocationRaw( 
            /* [retval][out] */ POINT *pixelLocationRaw) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PixelLocationRaw( 
            /* [in] */ POINT pixelLocationRaw) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_HimetricLocationRaw( 
            /* [retval][out] */ POINT *himetricLocationRaw) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_HimetricLocationRaw( 
            /* [in] */ POINT himetricLocationRaw) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_Time( 
            /* [retval][out] */ DWORD *time) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_Time( 
            /* [in] */ DWORD time) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_HistoryCount( 
            /* [retval][out] */ UINT32 *historyCount) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_HistoryCount( 
            /* [in] */ UINT32 historyCount) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_InputData( 
            /* [retval][out] */ INT32 *inputData) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_InputData( 
            /* [in] */ INT32 inputData) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_KeyStates( 
            /* [retval][out] */ DWORD *keyStates) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_KeyStates( 
            /* [in] */ DWORD keyStates) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PerformanceCount( 
            /* [retval][out] */ UINT64 *performanceCount) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PerformanceCount( 
            /* [in] */ UINT64 performanceCount) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_ButtonChangeKind( 
            /* [retval][out] */ INT32 *buttonChangeKind) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_ButtonChangeKind( 
            /* [in] */ INT32 buttonChangeKind) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PenFlags( 
            /* [retval][out] */ UINT32 *penFLags) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PenFlags( 
            /* [in] */ UINT32 penFLags) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PenMask( 
            /* [retval][out] */ UINT32 *penMask) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PenMask( 
            /* [in] */ UINT32 penMask) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PenPressure( 
            /* [retval][out] */ UINT32 *penPressure) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PenPressure( 
            /* [in] */ UINT32 penPressure) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PenRotation( 
            /* [retval][out] */ UINT32 *penRotation) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PenRotation( 
            /* [in] */ UINT32 penRotation) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PenTiltX( 
            /* [retval][out] */ INT32 *penTiltX) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PenTiltX( 
            /* [in] */ INT32 penTiltX) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_PenTiltY( 
            /* [retval][out] */ INT32 *penTiltY) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_PenTiltY( 
            /* [in] */ INT32 penTiltY) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_TouchFlags( 
            /* [retval][out] */ UINT32 *touchFlags) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_TouchFlags( 
            /* [in] */ UINT32 touchFlags) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_TouchMask( 
            /* [retval][out] */ UINT32 *touchMask) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_TouchMask( 
            /* [in] */ UINT32 touchMask) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_TouchContact( 
            /* [retval][out] */ RECT *touchContact) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_TouchContact( 
            /* [in] */ RECT touchContact) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_TouchContactRaw( 
            /* [retval][out] */ RECT *touchContactRaw) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_TouchContactRaw( 
            /* [in] */ RECT touchContactRaw) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_TouchOrientation( 
            /* [retval][out] */ UINT32 *touchOrientation) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_TouchOrientation( 
            /* [in] */ UINT32 touchOrientation) = 0;
        
        virtual /* [propget] */ HRESULT STDMETHODCALLTYPE get_TouchPressure( 
            /* [retval][out] */ UINT32 *touchPressure) = 0;
        
        virtual /* [propput] */ HRESULT STDMETHODCALLTYPE put_TouchPressure( 
            /* [in] */ UINT32 touchPressure) = 0;
        
    };
    
    
#else 	/* C style interface */

    typedef struct ICoreWebView2ExperimentalPointerInfoVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ REFIID riid,
            /* [annotation][iid_is][out] */ 
            _COM_Outptr_  void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            ICoreWebView2ExperimentalPointerInfo * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            ICoreWebView2ExperimentalPointerInfo * This);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PointerKind )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ DWORD *pointerKind);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PointerKind )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ DWORD pointerKind);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PointerId )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *pointerId);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PointerId )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 pointerId);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_FrameId )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *frameId);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_FrameId )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 frameId);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PointerFlags )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *pointerFlags);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PointerFlags )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 pointerFlags);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PointerDeviceRect )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ RECT *pointerDeviceRect);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PointerDeviceRect )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ RECT pointerDeviceRect);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_DisplayRect )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ RECT *displayRect);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_DisplayRect )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ RECT displayRect);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PixelLocation )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ POINT *pixelLocation);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PixelLocation )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ POINT pixelLocation);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_HimetricLocation )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ POINT *himetricLocation);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_HimetricLocation )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ POINT himetricLocation);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PixelLocationRaw )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ POINT *pixelLocationRaw);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PixelLocationRaw )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ POINT pixelLocationRaw);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_HimetricLocationRaw )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ POINT *himetricLocationRaw);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_HimetricLocationRaw )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ POINT himetricLocationRaw);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_Time )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ DWORD *time);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_Time )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ DWORD time);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_HistoryCount )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *historyCount);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_HistoryCount )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 historyCount);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_InputData )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ INT32 *inputData);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_InputData )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ INT32 inputData);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_KeyStates )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ DWORD *keyStates);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_KeyStates )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ DWORD keyStates);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PerformanceCount )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT64 *performanceCount);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PerformanceCount )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT64 performanceCount);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_ButtonChangeKind )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ INT32 *buttonChangeKind);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_ButtonChangeKind )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ INT32 buttonChangeKind);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PenFlags )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *penFLags);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PenFlags )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 penFLags);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PenMask )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *penMask);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PenMask )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 penMask);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PenPressure )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *penPressure);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PenPressure )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 penPressure);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PenRotation )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *penRotation);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PenRotation )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 penRotation);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PenTiltX )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ INT32 *penTiltX);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PenTiltX )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ INT32 penTiltX);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_PenTiltY )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ INT32 *penTiltY);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_PenTiltY )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ INT32 penTiltY);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_TouchFlags )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *touchFlags);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_TouchFlags )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 touchFlags);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_TouchMask )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *touchMask);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_TouchMask )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 touchMask);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_TouchContact )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ RECT *touchContact);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_TouchContact )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ RECT touchContact);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_TouchContactRaw )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ RECT *touchContactRaw);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_TouchContactRaw )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ RECT touchContactRaw);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_TouchOrientation )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *touchOrientation);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_TouchOrientation )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 touchOrientation);
        
        /* [propget] */ HRESULT ( STDMETHODCALLTYPE *get_TouchPressure )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [retval][out] */ UINT32 *touchPressure);
        
        /* [propput] */ HRESULT ( STDMETHODCALLTYPE *put_TouchPressure )( 
            ICoreWebView2ExperimentalPointerInfo * This,
            /* [in] */ UINT32 touchPressure);
        
        END_INTERFACE
    } ICoreWebView2ExperimentalPointerInfoVtbl;

    interface ICoreWebView2ExperimentalPointerInfo
    {
        CONST_VTBL struct ICoreWebView2ExperimentalPointerInfoVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define ICoreWebView2ExperimentalPointerInfo_QueryInterface(This,riid,ppvObject)	\
    ( (This)->lpVtbl -> QueryInterface(This,riid,ppvObject) ) 

#define ICoreWebView2ExperimentalPointerInfo_AddRef(This)	\
    ( (This)->lpVtbl -> AddRef(This) ) 

#define ICoreWebView2ExperimentalPointerInfo_Release(This)	\
    ( (This)->lpVtbl -> Release(This) ) 


#define ICoreWebView2ExperimentalPointerInfo_get_PointerKind(This,pointerKind)	\
    ( (This)->lpVtbl -> get_PointerKind(This,pointerKind) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PointerKind(This,pointerKind)	\
    ( (This)->lpVtbl -> put_PointerKind(This,pointerKind) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PointerId(This,pointerId)	\
    ( (This)->lpVtbl -> get_PointerId(This,pointerId) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PointerId(This,pointerId)	\
    ( (This)->lpVtbl -> put_PointerId(This,pointerId) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_FrameId(This,frameId)	\
    ( (This)->lpVtbl -> get_FrameId(This,frameId) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_FrameId(This,frameId)	\
    ( (This)->lpVtbl -> put_FrameId(This,frameId) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PointerFlags(This,pointerFlags)	\
    ( (This)->lpVtbl -> get_PointerFlags(This,pointerFlags) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PointerFlags(This,pointerFlags)	\
    ( (This)->lpVtbl -> put_PointerFlags(This,pointerFlags) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PointerDeviceRect(This,pointerDeviceRect)	\
    ( (This)->lpVtbl -> get_PointerDeviceRect(This,pointerDeviceRect) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PointerDeviceRect(This,pointerDeviceRect)	\
    ( (This)->lpVtbl -> put_PointerDeviceRect(This,pointerDeviceRect) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_DisplayRect(This,displayRect)	\
    ( (This)->lpVtbl -> get_DisplayRect(This,displayRect) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_DisplayRect(This,displayRect)	\
    ( (This)->lpVtbl -> put_DisplayRect(This,displayRect) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PixelLocation(This,pixelLocation)	\
    ( (This)->lpVtbl -> get_PixelLocation(This,pixelLocation) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PixelLocation(This,pixelLocation)	\
    ( (This)->lpVtbl -> put_PixelLocation(This,pixelLocation) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_HimetricLocation(This,himetricLocation)	\
    ( (This)->lpVtbl -> get_HimetricLocation(This,himetricLocation) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_HimetricLocation(This,himetricLocation)	\
    ( (This)->lpVtbl -> put_HimetricLocation(This,himetricLocation) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PixelLocationRaw(This,pixelLocationRaw)	\
    ( (This)->lpVtbl -> get_PixelLocationRaw(This,pixelLocationRaw) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PixelLocationRaw(This,pixelLocationRaw)	\
    ( (This)->lpVtbl -> put_PixelLocationRaw(This,pixelLocationRaw) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_HimetricLocationRaw(This,himetricLocationRaw)	\
    ( (This)->lpVtbl -> get_HimetricLocationRaw(This,himetricLocationRaw) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_HimetricLocationRaw(This,himetricLocationRaw)	\
    ( (This)->lpVtbl -> put_HimetricLocationRaw(This,himetricLocationRaw) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_Time(This,time)	\
    ( (This)->lpVtbl -> get_Time(This,time) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_Time(This,time)	\
    ( (This)->lpVtbl -> put_Time(This,time) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_HistoryCount(This,historyCount)	\
    ( (This)->lpVtbl -> get_HistoryCount(This,historyCount) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_HistoryCount(This,historyCount)	\
    ( (This)->lpVtbl -> put_HistoryCount(This,historyCount) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_InputData(This,inputData)	\
    ( (This)->lpVtbl -> get_InputData(This,inputData) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_InputData(This,inputData)	\
    ( (This)->lpVtbl -> put_InputData(This,inputData) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_KeyStates(This,keyStates)	\
    ( (This)->lpVtbl -> get_KeyStates(This,keyStates) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_KeyStates(This,keyStates)	\
    ( (This)->lpVtbl -> put_KeyStates(This,keyStates) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PerformanceCount(This,performanceCount)	\
    ( (This)->lpVtbl -> get_PerformanceCount(This,performanceCount) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PerformanceCount(This,performanceCount)	\
    ( (This)->lpVtbl -> put_PerformanceCount(This,performanceCount) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_ButtonChangeKind(This,buttonChangeKind)	\
    ( (This)->lpVtbl -> get_ButtonChangeKind(This,buttonChangeKind) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_ButtonChangeKind(This,buttonChangeKind)	\
    ( (This)->lpVtbl -> put_ButtonChangeKind(This,buttonChangeKind) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PenFlags(This,penFLags)	\
    ( (This)->lpVtbl -> get_PenFlags(This,penFLags) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PenFlags(This,penFLags)	\
    ( (This)->lpVtbl -> put_PenFlags(This,penFLags) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PenMask(This,penMask)	\
    ( (This)->lpVtbl -> get_PenMask(This,penMask) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PenMask(This,penMask)	\
    ( (This)->lpVtbl -> put_PenMask(This,penMask) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PenPressure(This,penPressure)	\
    ( (This)->lpVtbl -> get_PenPressure(This,penPressure) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PenPressure(This,penPressure)	\
    ( (This)->lpVtbl -> put_PenPressure(This,penPressure) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PenRotation(This,penRotation)	\
    ( (This)->lpVtbl -> get_PenRotation(This,penRotation) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PenRotation(This,penRotation)	\
    ( (This)->lpVtbl -> put_PenRotation(This,penRotation) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PenTiltX(This,penTiltX)	\
    ( (This)->lpVtbl -> get_PenTiltX(This,penTiltX) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PenTiltX(This,penTiltX)	\
    ( (This)->lpVtbl -> put_PenTiltX(This,penTiltX) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_PenTiltY(This,penTiltY)	\
    ( (This)->lpVtbl -> get_PenTiltY(This,penTiltY) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_PenTiltY(This,penTiltY)	\
    ( (This)->lpVtbl -> put_PenTiltY(This,penTiltY) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_TouchFlags(This,touchFlags)	\
    ( (This)->lpVtbl -> get_TouchFlags(This,touchFlags) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_TouchFlags(This,touchFlags)	\
    ( (This)->lpVtbl -> put_TouchFlags(This,touchFlags) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_TouchMask(This,touchMask)	\
    ( (This)->lpVtbl -> get_TouchMask(This,touchMask) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_TouchMask(This,touchMask)	\
    ( (This)->lpVtbl -> put_TouchMask(This,touchMask) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_TouchContact(This,touchContact)	\
    ( (This)->lpVtbl -> get_TouchContact(This,touchContact) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_TouchContact(This,touchContact)	\
    ( (This)->lpVtbl -> put_TouchContact(This,touchContact) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_TouchContactRaw(This,touchContactRaw)	\
    ( (This)->lpVtbl -> get_TouchContactRaw(This,touchContactRaw) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_TouchContactRaw(This,touchContactRaw)	\
    ( (This)->lpVtbl -> put_TouchContactRaw(This,touchContactRaw) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_TouchOrientation(This,touchOrientation)	\
    ( (This)->lpVtbl -> get_TouchOrientation(This,touchOrientation) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_TouchOrientation(This,touchOrientation)	\
    ( (This)->lpVtbl -> put_TouchOrientation(This,touchOrientation) ) 

#define ICoreWebView2ExperimentalPointerInfo_get_TouchPressure(This,touchPressure)	\
    ( (This)->lpVtbl -> get_TouchPressure(This,touchPressure) ) 

#define ICoreWebView2ExperimentalPointerInfo_put_TouchPressure(This,touchPressure)	\
    ( (This)->lpVtbl -> put_TouchPressure(This,touchPressure) ) 

#endif /* COBJMACROS */


#endif 	/* C style interface */




#endif 	/* __ICoreWebView2ExperimentalPointerInfo_INTERFACE_DEFINED__ */


#ifndef __ICoreWebView2ExperimentalCursorChangedEventHandler_INTERFACE_DEFINED__
#define __ICoreWebView2ExperimentalCursorChangedEventHandler_INTERFACE_DEFINED__

/* interface ICoreWebView2ExperimentalCursorChangedEventHandler */
/* [unique][object][uuid] */ 


EXTERN_C const IID IID_ICoreWebView2ExperimentalCursorChangedEventHandler;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("8F4840F1-9364-4629-AE12-4D21DFD69306")
    ICoreWebView2ExperimentalCursorChangedEventHandler : public IUnknown
    {
    public:
        virtual HRESULT STDMETHODCALLTYPE Invoke( 
            /* [in] */ ICoreWebView2ExperimentalCompositionController *sender,
            /* [in] */ IUnknown *args) = 0;
        
    };
    
    
#else 	/* C style interface */

    typedef struct ICoreWebView2ExperimentalCursorChangedEventHandlerVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            ICoreWebView2ExperimentalCursorChangedEventHandler * This,
            /* [in] */ REFIID riid,
            /* [annotation][iid_is][out] */ 
            _COM_Outptr_  void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            ICoreWebView2ExperimentalCursorChangedEventHandler * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            ICoreWebView2ExperimentalCursorChangedEventHandler * This);
        
        HRESULT ( STDMETHODCALLTYPE *Invoke )( 
            ICoreWebView2ExperimentalCursorChangedEventHandler * This,
            /* [in] */ ICoreWebView2ExperimentalCompositionController *sender,
            /* [in] */ IUnknown *args);
        
        END_INTERFACE
    } ICoreWebView2ExperimentalCursorChangedEventHandlerVtbl;

    interface ICoreWebView2ExperimentalCursorChangedEventHandler
    {
        CONST_VTBL struct ICoreWebView2ExperimentalCursorChangedEventHandlerVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define ICoreWebView2ExperimentalCursorChangedEventHandler_QueryInterface(This,riid,ppvObject)	\
    ( (This)->lpVtbl -> QueryInterface(This,riid,ppvObject) ) 

#define ICoreWebView2ExperimentalCursorChangedEventHandler_AddRef(This)	\
    ( (This)->lpVtbl -> AddRef(This) ) 

#define ICoreWebView2ExperimentalCursorChangedEventHandler_Release(This)	\
    ( (This)->lpVtbl -> Release(This) ) 


#define ICoreWebView2ExperimentalCursorChangedEventHandler_Invoke(This,sender,args)	\
    ( (This)->lpVtbl -> Invoke(This,sender,args) ) 

#endif /* COBJMACROS */


#endif 	/* C style interface */




#endif 	/* __ICoreWebView2ExperimentalCursorChangedEventHandler_INTERFACE_DEFINED__ */


#ifndef __ICoreWebView2ExperimentalEnvironment_INTERFACE_DEFINED__
#define __ICoreWebView2ExperimentalEnvironment_INTERFACE_DEFINED__

/* interface ICoreWebView2ExperimentalEnvironment */
/* [unique][object][uuid] */ 


EXTERN_C const IID IID_ICoreWebView2ExperimentalEnvironment;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("4987AF47-81DE-4833-B0A5-980CAEFB6EEE")
    ICoreWebView2ExperimentalEnvironment : public IUnknown
    {
    public:
        virtual HRESULT STDMETHODCALLTYPE CreateCoreWebView2CompositionController( 
            HWND parentWindow,
            ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler *handler) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE CreateCoreWebView2PointerInfo( 
            /* [retval][out] */ ICoreWebView2ExperimentalPointerInfo **pointerInfo) = 0;
        
        virtual HRESULT STDMETHODCALLTYPE GetProviderForHwnd( 
            /* [in] */ HWND hwnd,
            /* [retval][out] */ IUnknown **provider) = 0;
        
    };
    
    
#else 	/* C style interface */

    typedef struct ICoreWebView2ExperimentalEnvironmentVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            ICoreWebView2ExperimentalEnvironment * This,
            /* [in] */ REFIID riid,
            /* [annotation][iid_is][out] */ 
            _COM_Outptr_  void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            ICoreWebView2ExperimentalEnvironment * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            ICoreWebView2ExperimentalEnvironment * This);
        
        HRESULT ( STDMETHODCALLTYPE *CreateCoreWebView2CompositionController )( 
            ICoreWebView2ExperimentalEnvironment * This,
            HWND parentWindow,
            ICoreWebView2ExperimentalCreateCoreWebView2CompositionControllerCompletedHandler *handler);
        
        HRESULT ( STDMETHODCALLTYPE *CreateCoreWebView2PointerInfo )( 
            ICoreWebView2ExperimentalEnvironment * This,
            /* [retval][out] */ ICoreWebView2ExperimentalPointerInfo **pointerInfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetProviderForHwnd )( 
            ICoreWebView2ExperimentalEnvironment * This,
            /* [in] */ HWND hwnd,
            /* [retval][out] */ IUnknown **provider);
        
        END_INTERFACE
    } ICoreWebView2ExperimentalEnvironmentVtbl;

    interface ICoreWebView2ExperimentalEnvironment
    {
        CONST_VTBL struct ICoreWebView2ExperimentalEnvironmentVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define ICoreWebView2ExperimentalEnvironment_QueryInterface(This,riid,ppvObject)	\
    ( (This)->lpVtbl -> QueryInterface(This,riid,ppvObject) ) 

#define ICoreWebView2ExperimentalEnvironment_AddRef(This)	\
    ( (This)->lpVtbl -> AddRef(This) ) 

#define ICoreWebView2ExperimentalEnvironment_Release(This)	\
    ( (This)->lpVtbl -> Release(This) ) 


#define ICoreWebView2ExperimentalEnvironment_CreateCoreWebView2CompositionController(This,parentWindow,handler)	\
    ( (This)->lpVtbl -> CreateCoreWebView2CompositionController(This,parentWindow,handler) ) 

#define ICoreWebView2ExperimentalEnvironment_CreateCoreWebView2PointerInfo(This,pointerInfo)	\
    ( (This)->lpVtbl -> CreateCoreWebView2PointerInfo(This,pointerInfo) ) 

#define ICoreWebView2ExperimentalEnvironment_GetProviderForHwnd(This,hwnd,provider)	\
    ( (This)->lpVtbl -> GetProviderForHwnd(This,hwnd,provider) ) 

#endif /* COBJMACROS */


#endif 	/* C style interface */




#endif 	/* __ICoreWebView2ExperimentalEnvironment_INTERFACE_DEFINED__ */

#endif /* __WebView2Experimental_LIBRARY_DEFINED__ */

/* Additional Prototypes for ALL interfaces */

/* end of Additional Prototypes */

#ifdef __cplusplus
}
#endif

#endif


