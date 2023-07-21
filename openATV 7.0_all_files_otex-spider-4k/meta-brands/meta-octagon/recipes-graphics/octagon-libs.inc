DESCRIPTION = "shared lib for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS:${PN} = "curl"

PV = "${SRCDATE}"
PR = "r1"

inherit lib_package

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

SRC_URI = "file://sf8008m-hilib-20211202.zip"

S = "${WORKDIR}/hilib"

do_configure() {
}

do_compile() {
}

do_install() {
	install -d ${D}${libdir}
	install -m 0755 ${S}/*.so ${D}${libdir}/
	install -m 0755 ${S}/libavcodec.so.57 ${D}${libdir}/
	install -m 0755 ${S}/libavformat.so.57 ${D}${libdir}/
	install -m 0755 ${S}/libavutil.so.55 ${D}${libdir}/
	install -m 0755 ${S}/libswscale.so.4 ${D}${libdir}/
	install -m 0755 ${S}/libbluray.so.1 ${D}${libdir}/
}

do_package_qa() {
}

FILES:${PN} += "${libdir}/*"

INSANE_SKIP:${PN} = "already-stripped dev-so"
