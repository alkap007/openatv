SUMMARY = "exteplayer3 - media player for E2"
DESCRIPTION = "Core of movie player for E2 based on the libeplayer using the ffmpeg solution"
SECTION = "multimedia"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "ffmpeg libbluray"
RDEPENDS:${PN} = "ffmpeg libbluray"

DEPENDS += "octagon-libs-${MACHINE} libjpeg-turbo"
RDEPENDS:${PN} += "octagon-libs-${MACHINE} libjpeg-turbo"



inherit gitpkgv upx-compress

PV = "67+gitr${SRCPV}"
PKGV = "67+gitr${GITPKGV}"

PR = "r1"

SRCREV = "52666caaf08543122ad735e300acb2c2009c83d5"
SRC_URI = "git://github.com/e2iplayer/www;branch=master"

S = "${WORKDIR}/git"

SRC_URI:append:sf8008m = " file://exteplayer3.otex.patch"


SOURCE_FILES = "main/exteplayer.c"
SOURCE_FILES =+ "main/des.c"
SOURCE_FILES =+ "container/container.c"
SOURCE_FILES =+ "container/container_ffmpeg.c"
SOURCE_FILES =+ "manager/manager.c"
SOURCE_FILES =+ "manager/audio.c"
SOURCE_FILES =+ "manager/video.c"
SOURCE_FILES =+ "manager/subtitle.c"
SOURCE_FILES =+ "output/output_subtitle.c"
SOURCE_FILES =+ "output/graphic_subtitle.c"
SOURCE_FILES =+ "output/output.c"
SOURCE_FILES =+ "output/writer/common/pes.c"
SOURCE_FILES =+ "output/writer/common/misc.c"
SOURCE_FILES =+ "output/writer/common/writer.c"
SOURCE_FILES =+ "output/linuxdvb_buffering_hisi.c"
SOURCE_FILES =+ "playback/playback.c"
SOURCE_FILES =+ "external/ffmpeg/src/bitstream.c"
SOURCE_FILES =+ "external/ffmpeg/src/latmenc.c"
SOURCE_FILES =+ "external/ffmpeg/src/mpeg4audio.c"
SOURCE_FILES =+ "external/ffmpeg/src/xiph.c"
SOURCE_FILES =+ "external/flv2mpeg4/src/m4vencode.c"
SOURCE_FILES =+ "external/flv2mpeg4/src/flvdecoder.c"
SOURCE_FILES =+ "external/flv2mpeg4/src/dcprediction.c"
SOURCE_FILES =+ "external/flv2mpeg4/src/flv2mpeg4.c"
SOURCE_FILES =+ "external/plugins/src/png.c"
SOURCE_FILES =+ "hisi/hi_adp_mpi.c"
SOURCE_FILES =+ "hisi//hi_adp_hdmi.c"
SOURCE_FILES =+ "hisi/hi_adp_ini.c"
SOURCE_FILES =+ "output/linuxdvb_hisi.c"

do_compile() {
    ${CC} -lrt ${SOURCE_FILES} -DCONFIG_SUPPORT_CA_RELEASE=1 -DCHIP_TYPE_hi3798mv200 -DHI_DAC_CVBS -D_FILE_OFFSET_BITS=64 -D_LARGEFILE64_SOURCE -D_LARGEFILE_SOURCE -DHAVE_FLV2MPEG4_CONVERTER -I${S}/include -I${S}/external -I${S}/external/flv2mpeg4 -I${D}/${libdir} -I${D}/${includedir} -ljpeg8 -lhi_msp -lhi_common -lswscale -ldl -lpthread -lavformat -lavcodec -lavutil -lswresample -o exteplayer3 ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/exteplayer3 ${D}${bindir}
}

INSANE_SKIP:${PN} += "ldflags"
