// swift-tools-version:5.3
import PackageDescription

let version = "0.1.17"

let xcFrameworkUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(version)/kbcore-kmmbridge-\(version).zip"
let checksumUrl = "https://maven.pkg.github.com/dariuszszlag/KBMultiplatform/com/dariusz/kbcore-kmmbridge/\(version)/kbcore-kmmbridge-\(version).zip.md5"

let checksumString = "be0ad295101624cda502e015347e909d"

let packageName = "kbcore"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v14)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: xcFrameworkUrl,
            checksum: checksumString
        )
        ,
    ]
)